#ifndef SYNTAXHIGHLIGHTER_H
#define SYNTAXHIGHLIGHTER_H

#include <QSyntaxHighlighter>

class SyntaxHighlighter : public QSyntaxHighlighter
{
  Q_OBJECT

  public:
    SyntaxHighlighter(QTextDocument *parent = 0);

  protected:
    void highlightBlock(const QString &text);

  private:

    enum BlockState
    {
      valueState,
      nameState,
      otherState
    };

    QTextCharFormat symbolFormat;
    QTextCharFormat nameFormat;
    QTextCharFormat valueFormat;
};

#endif // SYNTAXHIGHLIGHTER_H
