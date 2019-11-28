//
// GOATTRACKER v2.75
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//

// TODO: Start ohne GUI

#define MAIN_C

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#include <goattrk2.h>

void *debug_thread(void *);

int main(int argc, char **argv) {
	pthread_t thread = { 0 };
	puts("Starting GoatTracker 2\n");

	if (pthread_create(&thread, NULL, debug_thread, NULL)) {
		perror("Starting a thread failed");
		exit(1);
	}

	return start_goattracker(argc, argv);
}

void stopme() {
	// Start GDB
	// set non-stop
	// break stopme
	// run
}

void *debug_thread(void *not_used) {
	while (1) {
		sleep(1);
		stopme();
	}

	return NULL;
}

