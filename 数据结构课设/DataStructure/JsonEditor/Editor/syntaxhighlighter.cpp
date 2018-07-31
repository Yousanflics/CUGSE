#include "syntaxhighlighter.h"

SyntaxHighlighter::SyntaxHighlighter(QTextDocument *parent) : QSyntaxHighlighter(parent)
{
  symbolFormat.setForeground(Qt::red);
  symbolFormat.setFontWeight(QFont::Bold);

  nameFormat.setForeground(Qt::blue);
  nameFormat.setFontWeight(QFont::Bold);
  nameFormat.setFontItalic (true);

  valueFormat.setForeground(Qt::darkGreen);
}

void SyntaxHighlighter::highlightBlock(const QString &text)
{
  QString textBlock = text;

  QRegExp expression("(\\{|\\}|\\[|\\]|\\:|\\,)");
  int index = expression.indexIn(textBlock);
  while (index >= 0)
  {
    int length = expression.matchedLength();
    setFormat(index, length, symbolFormat);
    index = expression.indexIn(textBlock, index + length);
  }

  textBlock.replace("\\\"", "  ");

  expression = QRegExp("\".*\" *\\:");
  expression.setMinimal(true);
  index = expression.indexIn(textBlock);
  while (index >= 0)
  {
    int length = expression.matchedLength();
    setFormat(index, length - 1, nameFormat);
    index = expression.indexIn(textBlock, index + length);
  }

  expression = QRegExp("\\: *\".*\"");
  expression.setMinimal(true);
  index = expression.indexIn(textBlock);
  while (index >= 0)
  {
    int length = expression.matchedLength();
    setFormat(index + 1, length - 1, valueFormat);
    index = expression.indexIn(textBlock, index + length);
  }
}
