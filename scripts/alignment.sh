module load hisat2
hisat2 -x $1 -U $2 -S $3

#1 - index name
#2 - fastq file
#3 - output file name