CC?=gcc
CFLAGS+=`sdl-config --cflags` -O0 -g3
LIBS+=`sdl-config --libs`


all: dat2inc datafile

datafile: datafile.c bme_end.c
	$(CC) $(CFLAGS) -o datafile datafile.c bme_end.c
	strip datafile

dat2inc: dat2inc.c
	$(CC) $(LIBS) -o dat2inc dat2inc.c
	strip dat2inc

clean:
	$(RM) dat2inc
	$(RM) datafile

