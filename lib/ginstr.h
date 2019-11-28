// TODO: Doku

#ifndef GINSTR_H
#define GINSTR_H

#ifndef GINSTR_C
extern int einum;		// current instrument
extern int eipos;		// selected instrument parameter (ac, sr, wavetable pos, ....)
extern int eicolumn;		// selected instrument parameter column (left nybble, right nybble)
extern INSTR instrcopybuffer;
#endif

void instrumentcommands(void);
void nextinstr(void);
void previnstr(void);
void clearinstr(int num);
void gotoinstr(int i);		// select instrument
void showinstrtable(void);

#endif
