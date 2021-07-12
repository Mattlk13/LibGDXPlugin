package com.gmail.blueboxware.libgdxplugin.inspections.kotlin

import com.gmail.blueboxware.libgdxplugin.inspections.isValidProperty
import com.gmail.blueboxware.libgdxplugin.message
import com.gmail.blueboxware.libgdxplugin.utils.asPlainString
import com.intellij.codeInspection.ProblemsHolder
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.KtVisitorVoid


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
class KotlinInvalidPropertyKeyInspection: LibGDXKotlinBaseInspection() {

  override fun getStaticDescription() = message("invalid.property.key.inspection.html.description")

  override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean) = object: KtVisitorVoid() {

    override fun visitStringTemplateExpression(expression: KtStringTemplateExpression) {

      if (!isValidProperty(expression)) {
        holder.registerProblem(
                expression,
                message(
                        "invalid.property.key.inspection.problem.descriptor", expression.asPlainString()
                        ?: ""
                )
        )
      }

    }

  }

}