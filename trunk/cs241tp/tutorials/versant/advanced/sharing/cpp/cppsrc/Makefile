# Copyright (c) 1998-2005 Versant Corporation. All Rights Reserved

include ..\makecom.inc

GDB1= cppdb

TARGETS = example1_main$(EXE) example2_main$(EXE) example3_main$(EXE)
	 
OBJECTS = vehicle$(OBJ) car$(OBJ) employee$(OBJ) floorPlan$(OBJ)

all: $(TARGETS)

build_demo : all

clean:
	-del $(TARGETS) $(TEMP_FILES) \
	example1.cxx example2.cxx example3.cxx 2> nul

example1$(OBJ): example1.cxx
	$(CXXCOMP) $(TEMPDIR1) -c example1.cxx

example2$(OBJ): example2.cxx
	$(CXXCOMP) $(TEMPDIR1) -c example2.cxx

example3$(OBJ): example3.cxx
	$(CXXCOMP) $(TEMPDIR1) -c example3.cxx

example1.cxx: example1.imp

example2.cxx: example2.imp

example3.cxx: example3.imp

SOURCE_FILES=  \
example1_main.cxx example2_main.cxx example3_main.cxx \
vehicle.cxx vehicle.h car.cxx car.h employee.cxx employee.h \
floorPlan.cxx floorPlan.h

EXAMPLE1_MAIN_OBJECTS= example1_main$(OBJ) vehicle$(OBJ) \
	example1$(OBJ)

EXAMPLE2_MAIN_OBJECTS= example2_main$(OBJ) vehicle$(OBJ) car$(OBJ) \
	example2$(OBJ)

EXAMPLE3_MAIN_OBJECTS= example3_main$(OBJ) employee$(OBJ) floorPlan$(OBJ) \
	example3$(OBJ)

example1_main$(EXE): $(EXAMPLE1_MAIN_OBJECTS)
	$(CXXLINK)  $(EXAMPLE1_MAIN_OBJECTS) $(CXX_LIBRARY)

example2_main$(EXE): $(EXAMPLE2_MAIN_OBJECTS)
	$(CXXLINK)  $(EXAMPLE2_MAIN_OBJECTS) $(CXX_LIBRARY)

example3_main$(EXE): $(EXAMPLE3_MAIN_OBJECTS)
	$(CXXLINK)  $(EXAMPLE3_MAIN_OBJECTS) $(CXX_LIBRARY)

example1_main$(OBJ): example1_main.cxx vehicle.h

example2_main$(OBJ): example2_main.cxx vehicle.h car.h

example3_main$(OBJ): example3_main.cxx employee.h floorPlan.h

vehicle$(OBJ): vehicle.cxx vehicle.h 

car$(OBJ): car.cxx car.h 

employee$(OBJ): employee.cxx employee.h 

floorPlan$(OBJ): floorPlan.cxx floorPlan.h 

run: all run_part0 run_part1 run_part2 run_part3

run_part0:
	-removedb -f -rmdir $(GDB1)
	makedb -g $(GDB1)
	createdb $(GDB1)

run_part1:
	sch2db -y -f -D $(GDB1) example1.sch
	example1_main $(GDB1) create
	example1_main $(GDB1)

run_part2:
	sch2db -y -f -D $(GDB1) example2.sch
	example2_main $(GDB1) create
	example2_main $(GDB1)

run_part3:
	sch2db -y -f -D $(GDB1) example3.sch
	example3_main $(GDB1) create
	example3_main $(GDB1)

run_demo:
	$(MAKE) EXTRA_FLAGS="$(EXTRA_ANSI_FLAGS)" EXTRA_ANSI_LIB=ansi run
