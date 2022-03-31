module load StdEnv/2020
module load nixpkgs/16.09
module load gcc/9.3.0
module load gcc/7.3.0
module load hisat2

#fasta = $1
#index = $2
#reads = $3
#output = $4

hisat2-build $1 $2

hisat2 -x $2 -U $3 -S $4

