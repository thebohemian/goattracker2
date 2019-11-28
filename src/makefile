#
# Linux makefile
#

CFLAGS+=`sdl-config --cflags` -I../lib -O0 -g3
PREFIX=../linux/
SUFFIX=
LIBS+=`sdl-config --libs` -pthread
DYN_LIB=-L../lib -lgoat2
DL_LIB=-ldl

main: ../lib/libgoat2.so main.o
	gcc main.o -o main $(DYN_LIB) $(LIBS)

main-dynamicload: ../lib/libgoat2.so main-dynamicload.o
	gcc main-dynamicload.o -o main-dynamicload $(DL_LIB) $(LIBS)

clean:
	$(RM) *.o
	$(RM) main main-dynamicload
