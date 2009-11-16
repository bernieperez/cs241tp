
# Copyright (c) 1994-2005 Versant Corporation. All Rights Reserved
#
# Makefile for C++/VERSANT for Microsoft Visual C++ compiler
#
# You must define VERSANT_RELEASE_DIR or VERSANT_ROOT
#
# Defaults are set for
#       -- do not use Check Facility
#       -- use 2-process library
#
# The default is to make a production version.
#
# To make a Checking version (linked with Versant checking library)
# use this on the "make" command line:
#       CXXCHECKING=1
#
# To compile with debug option:
#       CXXDEBUG=1
#
# The default is to use 2-process library:
#       TWO_PROCESS=fe
#
# To link with one-process library, undefine TWO_PROCESS
#       TWO_PROCESS=
#

# suffix for executable files
EXE=.exe

# suffix for object files
OBJ=.obj

# set this variable for VERSANT_ROOT if not set as
# environment variable
#
# remove the "#" sign to include VERSANT_ROOT variable
# VERSANT_ROOT=

!if "$(VERSANT_ROOT)" == ""
!    ERROR VERSANT_ROOT variable not initialized
!endif

!if "$(VERSANT_RELEASE_DIR)" == ""
VERSANT_RELEASE_DIR=$(VERSANT_ROOT)
!endif

# set this variable to "fe" to link with 2-process library
TWO_PROCESS=fe

EXTRA_ANSI_FLAGS=-DVERSANT_ANSI $(ANSI_FLAGS)
!if "$(CXXCHECKING)"=="1"
CHK_FLAG= -DCHECKING
!else
CHK_FLAG=
!endif

!if "$(TWO_PROCESS)"=="fe"
!if "$(CXXCHECKING)"=="1"
LIBS= $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscchk.lib \
      $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscvcch.lib \
      $(VERSANT_RELEASE_DIR)\lib\libosc$(TWO_PROCESS).lib
!else
LIBS= $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\msccls.lib \
      $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscvcoll.lib \
      $(VERSANT_RELEASE_DIR)\lib\libosc$(TWO_PROCESS).lib
!endif
!else
!if "$(CXXCHECKING)"=="1"
LIBS= $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscchk1p.lib \
      $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscvcc1p.lib \
      $(VERSANT_RELEASE_DIR)\lib\libosc$(TWO_PROCESS).lib
!else
LIBS= $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\msccls1p.lib \
      $(VERSANT_RELEASE_DIR)\lib\$(EXTRA_ANSI_LIB)\mscvc1p.lib \
      $(VERSANT_RELEASE_DIR)\lib\libosc$(TWO_PROCESS).lib
!endif
!endif

MSC_LINK_LIBRARY=kernel32.lib wsock32.lib user32.lib

CXX_LIBRARY=$(LIBS) $(MSC_LINK_LIBRARY)
CXX_CHECK_LIBRARY=$(LIBS) $(MSC_LINK_LIBRARY)

# directory locations
HDIR=-I$(VERSANT_RELEASE_DIR)\h -I.

COMMON_FLAGS=-nologo -W3 -vmg -GX

!if "$(CXXDEBUG)"=="1"
VPP_FLAG =$(COMMON_FLAGS) -Od -Zi -MDd
LINK_FLAG=-nologo -incremental:no -debug:full -pdb:none
!else
VPP_FLAG =$(COMMON_FLAGS) -MD
LINK_FLAG=-nologo -incremental:no
!endif

SCHFLAG=-DWIN32_LEAN_AND_MEAN -DWIN32_EXTRA_LEAN
CXXCOMP=cl $(VPP_FLAG) $(CHK_FLAG) $(HDIR) $(EXTRA_FLAGS)
CXXLINK=link $(LINK_FLAG) -out:$@ -entry:mainCRTStartup -subsystem:console

# use CXXCOMPSCH to compile the output of schcomp
CXXCOMPSCH= cl $(VPP_FLAG) $(CHK_FLAG) $(HDIR) $(EXTRA_FLAGS)

.SUFFIXES: .obj .c .cxx .cpp .imp

.imp.cxx:
	schcomp.exe $(SCHFLAG) $(VPP_FLAG) $(CHK_FLAG) $(HDIR) $(EXTRA_FLAGS) $*.imp

.cxx.obj:
	$(CXXCOMP) -c $*.cxx

.cpp.obj:
	$(CXXCOMP) -c $*.cpp

.c.obj:
	$(CXXCOMP) -c $*.c
 
# Command for file deletion
DELETE=del

# Command modifier for discarding the output of delete
PIPE_TO_NULL= 2> NUL

TEMP_FILES= *.exe *.pdb *.ilk *.obj *.sch *.i *.i0 *.i1 *.i2 *.i3 *.pch schema.cxx
