// TODO: Doku
// TODO: Verwendung checken

#ifndef GBINDING_H
#define GBINDING_H

/** Place this in front of every function that is to be exported */
#define DLL_PUBLIC __attribute__ ((visibility ("default")))

extern DLL_PUBLIC int start_goattracker(int, char **);
extern DLL_PUBLIC void stop_goattracker();

extern DLL_PUBLIC void play_song();
extern DLL_PUBLIC void stop_song();

#endif
