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
