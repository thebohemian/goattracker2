#
# Linux makefile
#

CFLAGS+=`sdl-config --cflags`
PREFIX=../linux/
SUFFIX=
LIBS+=`sdl-config --libs`

include makefile.common

main.o: main.c
	gcc -c main.c -o main.o

main: libgoat2.so main.o
	gcc main.o $(CFLAGS) -L. -lgoat2 $(LIBS) -o main

clean2:
	$(RM) main
