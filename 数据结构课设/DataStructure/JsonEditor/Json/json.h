#ifndef JSON_H
#define JSON_H

#include "json_global.h"
#include <QtScript/QScriptEngine>
#include <QtScript/QScriptValueIterator>
#include <QMap>
#include <QList>
#include <QVariant>

class JSONSHARED_EXPORT Json
{

public:
    Json();
    QString encode(const QMap<QString,QVariant> &map);
    QMap<QString, QVariant> decode(const QString &jsonStr);
    QScriptValue encodeInner(const QMap<QString,QVariant> &map, QScriptEngine* engine);
    QMap<QString, QVariant> decodeInner(QScriptValue object);
    QList<QVariant> decodeInnerToList(QScriptValue arrayValue);

};

#endif // JSON_H
