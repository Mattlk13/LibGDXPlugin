package com.gmail.blueboxware.libgdxplugin.skin

import com.gmail.blueboxware.libgdxplugin.LibGDXCodeInsightFixtureTestCase
import com.gmail.blueboxware.libgdxplugin.filetypes.skin.inspections.*
import com.gmail.blueboxware.libgdxplugin.message

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
class TestSuppressQuickfixes: LibGDXCodeInsightFixtureTestCase() {

  fun testSuppressForObject1() {
    myFixture.enableInspections(SkinNonExistingFieldInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForObject2() {
    myFixture.enableInspections(SkinMissingPropertyInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForObject3() {
    myFixture.copyFileToProject("ColorArrayHolder.java")
    myFixture.enableInspections(SkinTypeInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForObject4() {
    myFixture.enableInspections(SkinNonExistingFieldInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForObject5() {
    myFixture.enableInspections(SkinNonExistingFieldInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForClassSpec1() {
    myFixture.enableInspections(SkinDuplicateResourceNameInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForClassSpec2() {
    myFixture.enableInspections(SkinDuplicateResourceNameInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForClassSpec3() {
    myFixture.enableInspections(SkinDuplicateResourceNameInspection())
    doTest(message("suppress.object"))
  }

  fun testSuppressForFile1() {
    myFixture.enableInspections(SkinNonExistingClassInspection())
    doTest(message("suppress.file"))
  }

  fun testSuppressForFile2() {
    myFixture.enableInspections(SkinDuplicateResourceNameInspection())
    doTest(message("suppress.file"))
  }

  fun testSuppressForFile3() {
    myFixture.copyFileToProject("ColorArrayHolder.java")
    myFixture.enableInspections(SkinTypeInspection())
    doTest(message("suppress.file"))
  }

  fun doTest(familyName: String) {
    myFixture.configureByFile(getTestName(true) + ".skin")
    for (intention in myFixture.availableIntentions) {
      if (intention.familyName == familyName) {
        myFixture.launchAction(intention)
        myFixture.checkResultByFile(getTestName(true) + ".after")
        return
      }
    }

    fail("Suppress intention '$familyName' not found")

  }

  override fun setUp() {
    super.setUp()
    addLibGDX()
  }

  override fun getBasePath() = "filetypes/skin/suppressQuickfixes"

}