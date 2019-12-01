#include <stdio.h>

#include "goattrk2.h"

void stop_goattracker() {
	// Simply sets a flag to allow the main loop to end
	exitprogram = 1;
}

void play_song() {
	initsong(0, PLAY_BEGINNING);
}

void stop_song() {
	if (isplaying()) {
		stopsong();
	}
}

void *get_instrument(int instrumentNumber) {
	if (instrumentNumber < 0 ||
		instrumentNumber >= MAX_INSTR) {
		return NULL;
	}

	return (void *) &instr[instrumentNumber];
}

void load_binary_song(const char *filename) {
	printf("loading song: %s\n", filename);

	do_loadsong(filename);
}

unsigned char *get_left_table(int index) {
	if (index < 0 || index >= MAX_TABLES) {
		return NULL;
	}

	return ltable[index];
}

unsigned char *get_right_table(int index) {
	if (index < 0 || index >= MAX_TABLES) {
		return NULL;
	}

	return rtable[index];
}

unsigned char *get_pattern(int index) {
	if (index < 0 || index >= MAX_PATT) {
		return NULL;
	}

	return pattern[index];
}

int *get_pattern_lengths() {
	return pattlen;
}


unsigned char *get_songorder(int songIndex, int channelIndex) {
	if (songIndex < 0 || songIndex >= MAX_SONGS) {
		return NULL;
	}
	if (channelIndex < 0 || channelIndex >= MAX_CHN) {
		return NULL;
	}

	return songorder[songIndex][channelIndex];
}

int *get_song_lengths(int songIndex) {
	if (songIndex < 0 || songIndex >= MAX_SONGS) {
		return NULL;
	}

	return songlen[songIndex];
}


char *get_song_name() {
	return songname;
}

char *get_author_name() {
	return authorname;
}

char *get_copyright_name() {
	return copyrightname;
}

