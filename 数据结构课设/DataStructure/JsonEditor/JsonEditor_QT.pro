#BUILDNO = $$(version.sh)#
DEFINES += BUILD=$${BUILDNO}

TEMPLATE = subdirs

SUBDIRS += Json
SUBDIRS += Editor
