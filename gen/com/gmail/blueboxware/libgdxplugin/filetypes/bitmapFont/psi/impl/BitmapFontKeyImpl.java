// This is a generated file. Not intended for manual editing.
package com.gmail.blueboxware.libgdxplugin.filetypes.bitmapFont.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.gmail.blueboxware.libgdxplugin.filetypes.bitmapFont.BitmapFontElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.gmail.blueboxware.libgdxplugin.filetypes.bitmapFont.psi.*;

public class BitmapFontKeyImpl extends ASTWrapperPsiElement implements BitmapFontKey {

  public BitmapFontKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BitmapFontElementVisitor visitor) {
    visitor.visitKey(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BitmapFontElementVisitor) accept((BitmapFontElementVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getUnquotedString() {
    return findNotNullChildByType(UNQUOTED_STRING);
  }

}
