package com.gmail.blueboxware.libgdxplugin.filetypes.skin

import com.gmail.blueboxware.libgdxplugin.utils.asString
import com.gmail.blueboxware.libgdxplugin.utils.isLibGDX199
import com.gmail.blueboxware.libgdxplugin.utils.isStringType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiImmediateClassType
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.searches.ClassInheritorsSearch
import com.intellij.psi.search.searches.ReferencesSearch
import org.jetbrains.kotlin.idea.caches.resolve.analyzeFully
import org.jetbrains.kotlin.js.descriptorUtils.getJetTypeFqName
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.psiUtil.getOrCreateValueArgumentList
import org.jetbrains.kotlin.psi.psiUtil.isPlain
import org.jetbrains.kotlin.psi.psiUtil.plainContent
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.SimpleType
import org.jetbrains.kotlin.types.checker.isClassType


/*
 * Copyright 2018 Blue Box Ware
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
internal const val PROPERTY_NAME_PARENT = "parent"

internal const val PROPERTY_NAME_FONT_FILE = "file"
internal const val PROPERTY_NAME_FONT_SCALED_SIZE = "scaledSize"
internal const val PROPERTY_NAME_FONT_MARKUP = "markupEnabled"
internal const val PROPERTY_NAME_FONT_FLIP = "flip"

internal const val PROPERTY_NAME_TINTED_DRAWABLE_NAME = "name"
internal const val PROPERTY_NAME_TINTED_DRAWABLE_COLOR = "color"

internal fun Project.getSkinTag2ClassMap(): TagMap? =
        if (isLibGDX199()) {
          collectCustomTags().apply {
            addAll(DEFAULT_TAGGED_CLASSES_NAMES)
          }
        } else {
          null
        }

private val DEFAULT_TAGGED_CLASSES_NAMES: Collection<Pair<String, String>> = listOf(
        "graphics.g2d.BitmapFont",
        "graphics.Color",
        "scenes.scene2d.ui.Skin.TintedDrawable",
        "scene2d.utils.NinePatchDrawable",
        "scenes.scene2d.utils.SpriteDrawable",
        "scenes.scene2d.utils.TextureRegionDrawable",
        "scenes.scene2d.utils.TiledDrawable",
        "scenes.scene2d.ui.Button.ButtonStyle",
        "scenes.scene2d.ui.CheckBox.CheckBoxStyle",
        "scenes.scene2d.ui.ImageButton.ImageButtonStyle",
        "scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle",
        "scenes.scene2d.ui.Label.LabelStyle",
        "scenes.scene2d.ui.List.ListStyle",
        "scenes.scene2d.ui.ProgressBar.ProgressBarStyle",
        "scenes.scene2d.ui.ScrollPane.ScrollPaneStyle",
        "scenes.scene2d.ui.SelectBox.SelectBoxStyle",
        "scenes.scene2d.ui.Slider.SliderStyle",
        "scenes.scene2d.ui.SplitPane.SplitPaneStyle",
        "scenes.scene2d.ui.TextButton.TextButtonStyle",
        "scenes.scene2d.ui.TextField.TextFieldStyle",
        "scenes.scene2d.ui.TextTooltip.TextTooltipStyle",
        "scenes.scene2d.ui.Touchpad.TouchpadStyle",
        "scenes.scene2d.ui.Tree.TreeStyle",
        "scenes.scene2d.ui.Window.WindowStyle"
).map { StringUtil.getShortName(it) to  "com.badlogic.gdx.$it" }

private fun Project.collectCustomTags(): TagMap {

  val tagMap = TagMap()

  JavaPsiFacade.getInstance(this).findClasses("com.badlogic.gdx.utils.Json", GlobalSearchScope.allScope(this)).forEach { jsonClass ->

    ClassInheritorsSearch.search(jsonClass).toMutableList().apply { add(jsonClass) }.forEach { jsonSubClass ->

      jsonSubClass.findMethodsByName("addClassTag", false).forEach { addTagMethod ->

        ReferencesSearch.search(addTagMethod).forEach { reference ->

          (reference.element.context as? PsiMethodCallExpression)?.argumentList?.let { argumentsList ->

            if (argumentsList.expressions.size == 2
                    && argumentsList.expressionTypes.getOrNull(0)?.isStringType(reference.element) == true
                    && argumentsList.expressionTypes.getOrNull(1) is PsiImmediateClassType) {


              (argumentsList.expressions.getOrNull(0) as? PsiLiteralExpression)?.asString()?.let { tag ->
                ((argumentsList.expressions.getOrNull(1) as? PsiClassObjectAccessExpression)?.operand?.type as? PsiClassType)?.resolve()?.let { clazz ->
                  clazz.qualifiedName?.let { fqName ->
                    tagMap.add(tag, fqName)
                  }
                }
              }

            }

          }

          (reference.element.context as? KtCallExpression)?.getOrCreateValueArgumentList()?.arguments?.let { arguments ->

            (arguments.getOrNull(0)?.getArgumentExpression() as? KtStringTemplateExpression)?.takeIf { it.isPlain() }?.let { firstArgument ->

              arguments.getOrNull(1)?.getArgumentExpression()?.let { secondArgument ->

                (secondArgument.getType(secondArgument.analyzeFully()) as? SimpleType)?.takeIf { it.isClassType }?.arguments?.firstOrNull()?.type?.getJetTypeFqName(false)?.let { fqName ->
                  tagMap.add(firstArgument.plainContent, fqName)
                }

              }

            }

          }

        }
      }

    }
  }

  return tagMap

}
