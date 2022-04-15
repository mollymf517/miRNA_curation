#This file uses hisat2-build to create alignment indexes

#Input: 
    #1 should be a fasta file
    #2 should be a string/index name

module load hisat2
hisat2-build $1 $2

