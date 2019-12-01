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

extern DLL_PUBLIC void load_binary_song(const char *);

extern DLL_PUBLIC void *get_instrument(int);

extern DLL_PUBLIC unsigned char *get_pattern(int);
extern DLL_PUBLIC int *get_pattern_lengths();

extern DLL_PUBLIC unsigned char *get_songorder(int, int);
extern DLL_PUBLIC int *get_song_lengths(int);

extern DLL_PUBLIC unsigned char *get_left_table(int);
extern DLL_PUBLIC unsigned char *get_right_table(int);


extern DLL_PUBLIC char *get_song_name();
extern DLL_PUBLIC char *get_author_name();
extern DLL_PUBLIC char *get_copyright_name();

#endif

