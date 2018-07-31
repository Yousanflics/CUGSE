/****************************************************************************
** Meta object code from reading C++ file 'dialog1.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.8.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../BinaryTree/dialog1.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'dialog1.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.8.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_Dialog1_t {
    QByteArrayData data[18];
    char stringdata0[151];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_Dialog1_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_Dialog1_t qt_meta_stringdata_Dialog1 = {
    {
QT_MOC_LITERAL(0, 0, 7), // "Dialog1"
QT_MOC_LITERAL(1, 8, 9), // "paintTree"
QT_MOC_LITERAL(2, 18, 0), // ""
QT_MOC_LITERAL(3, 19, 14), // "BinTree<char>*"
QT_MOC_LITERAL(4, 34, 1), // "p"
QT_MOC_LITERAL(5, 36, 10), // "CreateTree"
QT_MOC_LITERAL(6, 47, 8), // "SwapTree"
QT_MOC_LITERAL(7, 56, 9), // "OutputAns"
QT_MOC_LITERAL(8, 66, 7), // "JudgeOn"
QT_MOC_LITERAL(9, 74, 1), // "x"
QT_MOC_LITERAL(10, 76, 10), // "GetNewTree"
QT_MOC_LITERAL(11, 87, 9), // "BuildNewT"
QT_MOC_LITERAL(12, 97, 4), // "opea"
QT_MOC_LITERAL(13, 102, 18), // "BinTreeNode<char>*"
QT_MOC_LITERAL(14, 121, 7), // "subTree"
QT_MOC_LITERAL(15, 129, 10), // "GetCurData"
QT_MOC_LITERAL(16, 140, 5), // "paint"
QT_MOC_LITERAL(17, 146, 4) // "find"

    },
    "Dialog1\0paintTree\0\0BinTree<char>*\0p\0"
    "CreateTree\0SwapTree\0OutputAns\0JudgeOn\0"
    "x\0GetNewTree\0BuildNewT\0opea\0"
    "BinTreeNode<char>*\0subTree\0GetCurData\0"
    "paint\0find"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_Dialog1[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
      11,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    1,   69,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       5,    0,   72,    2, 0x08 /* Private */,
       6,    0,   73,    2, 0x08 /* Private */,
       7,    0,   74,    2, 0x08 /* Private */,
       8,    1,   75,    2, 0x08 /* Private */,
      10,    0,   78,    2, 0x08 /* Private */,
      11,    0,   79,    2, 0x08 /* Private */,
      12,    1,   80,    2, 0x08 /* Private */,
      15,    0,   83,    2, 0x08 /* Private */,
      16,    0,   84,    2, 0x08 /* Private */,
      17,    0,   85,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void, 0x80000000 | 3,    4,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    9,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 13,   14,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void Dialog1::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        Dialog1 *_t = static_cast<Dialog1 *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->paintTree((*reinterpret_cast< BinTree<char>*(*)>(_a[1]))); break;
        case 1: _t->CreateTree(); break;
        case 2: _t->SwapTree(); break;
        case 3: _t->OutputAns(); break;
        case 4: _t->JudgeOn((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 5: _t->GetNewTree(); break;
        case 6: _t->BuildNewT(); break;
        case 7: _t->opea((*reinterpret_cast< BinTreeNode<char>*(*)>(_a[1]))); break;
        case 8: _t->GetCurData(); break;
        case 9: _t->paint(); break;
        case 10: _t->find(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (Dialog1::*_t)(BinTree<char> * );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&Dialog1::paintTree)) {
                *result = 0;
                return;
            }
        }
    }
}

const QMetaObject Dialog1::staticMetaObject = {
    { &QDialog::staticMetaObject, qt_meta_stringdata_Dialog1.data,
      qt_meta_data_Dialog1,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *Dialog1::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *Dialog1::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_Dialog1.stringdata0))
        return static_cast<void*>(const_cast< Dialog1*>(this));
    return QDialog::qt_metacast(_clname);
}

int Dialog1::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 11)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 11;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 11)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 11;
    }
    return _id;
}

// SIGNAL 0
void Dialog1::paintTree(BinTree<char> * _t1)
{
    void *_a[] = { Q_NULLPTR, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
