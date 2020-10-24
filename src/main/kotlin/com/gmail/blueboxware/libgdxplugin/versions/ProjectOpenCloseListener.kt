package com.gmail.blueboxware.libgdxplugin.versions

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener


/*
 * Copyright 2020 Blue Box Ware
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
class ProjectOpenCloseListener: ProjectManagerListener {

  override fun projectOpened(project: Project) =
          project.service<VersionService>().projectOpened()

  override fun projectClosed(project: Project) =
          project.service<VersionService>().projectClosed()
}