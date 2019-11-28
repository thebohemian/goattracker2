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
#include <dlfcn.h>

// function export from the *.so
typedef int (*start_goattracker_ptr)(int, char **);

int main(int argc, char **argv) {
	char *error;
	start_goattracker_ptr start_goattracker;
	void *handle;
	int result;

	// open the *.so
	handle = dlopen ("../lib/libgoat2.so", RTLD_LAZY);
	if (!handle) {
		fputs (dlerror(), stderr);
	        exit(1);
	}
	
	// get the function address and make it ready for use
	start_goattracker = (start_goattracker_ptr) dlsym(handle, "start_goattracker");
	if ((error = dlerror()) != NULL)  {
		fputs(error, stderr);
		exit(1);
	}

	// call the function in *.so
	result = start_goattracker(argc, argv);

	// remember to free the resource
	dlclose(handle);

	return result;
}

