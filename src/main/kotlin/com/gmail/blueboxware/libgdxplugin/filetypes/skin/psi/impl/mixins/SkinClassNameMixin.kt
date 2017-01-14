package com.gmail.blueboxware.libgdxplugin.filetypes.skin.psi.impl.mixins

import com.gmail.blueboxware.libgdxplugin.filetypes.skin.psi.SkinClassName
import com.gmail.blueboxware.libgdxplugin.filetypes.skin.psi.impl.SkinElementImpl
import com.gmail.blueboxware.libgdxplugin.filetypes.skin.references.SkinJavaClassReference
import com.intellij.lang.ASTNode
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.search.GlobalSearchScope

/*
 * Copyright 2017 Blue Box Ware
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
abstract class SkinClassNameMixin(node: ASTNode) : SkinClassName, SkinElementImpl(node) {

  override fun getValue() = stringLiteral.value

  override fun resolve(): PsiClass? = JavaPsiFacade.getInstance(project).findClass(SkinClassSpecificationMixin.removeDollarFromClassName(value), GlobalSearchScope.allScope(project))

  override fun multiResolve(): Array<PsiClass> = JavaPsiFacade.getInstance(project).findClasses(SkinClassSpecificationMixin.removeDollarFromClassName(value), GlobalSearchScope.allScope(project))

  override fun getReference(): SkinJavaClassReference = SkinJavaClassReference(this)
}