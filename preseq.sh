#!/bin/bash

cd
cd /global/home/hpc4982/resources
#echo "arg 0: $0"
#echo "arg 1: $1"
module load StdEnv/2020
module load nixpkgs/16.09
module load gcc/9.3.0
module load gcc/7.3.0
module load blast+/2.10.1
seq=$(echo | blastdbcmd -db allSeqs -entry $1 -outfmt %s)
echo $seq