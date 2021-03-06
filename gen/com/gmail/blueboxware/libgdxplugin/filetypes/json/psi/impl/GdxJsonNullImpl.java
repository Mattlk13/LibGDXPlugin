// This is a generated file. Not intended for manual editing.
package com.gmail.blueboxware.libgdxplugin.filetypes.json.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.gmail.blueboxware.libgdxplugin.filetypes.json.GdxJsonElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.gmail.blueboxware.libgdxplugin.filetypes.json.psi.*;

public class GdxJsonNullImpl extends ASTWrapperPsiElement implements GdxJsonNull {

  public GdxJsonNullImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GdxJsonElementVisitor visitor) {
    visitor.visitNull(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GdxJsonElementVisitor) accept((GdxJsonElementVisitor)visitor);
    else super.accept(visitor);
  }

}
