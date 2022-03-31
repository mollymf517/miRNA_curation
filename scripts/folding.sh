module load viennarna

RNAfold $1

#add annotations
#if you get the location of miRNA and miRNA* from the alignment against the precursor
	#you should teoretically be able to use RNAplot to make these sections a different
	#color. The commented out code below is a rough example of how to 
#RNAplot --pre "mS mEnd 8 BLUE sS sEnd 8 RED" < file.ps
#this code creates a different ps file for every sequence in fasta file