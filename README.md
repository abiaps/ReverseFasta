# ReverseFasta

# Introduction to FASTA https://zhanglab.ccmb.med.umich.edu/FASTA/

# This project will focus on reversing the sequences given in an input .fasta file.

  Assumptions:

  Sequences do not contain whitespaces according to https://pacbiofileformats.readthedocs.io/en/3.0/FASTA.html or any special 
  characters except a few mentioned in the link. The scope of this project does not include validation the sequences underneath the 
  description of protein. 

  Description of a protein starts with an identifier '>'. If not, this is considered to be an invalid sequence and
  skipped to next valid sequence if present. The description can contain spaces and special characters as long as it 
  starts with '>'

  Output sequences are incremented with count from 1. If a sequence cannot be reversed, it increments the count anyways.
  Each line can contain only 80 characters.

  >REV_1 reversed 

  The above line is an example of sequence identifier in the output file.

  # Run this project using Ant build

  # Ant can be installed from  https://ant.apache.org/bindownload.cgi

  # Set ANT_HOME in system variables

  # Successful installation of Ant can be checked by $ant -v in cmd

  # Check if java installed on your machine using $java -version

  # Clone this project

  # Go to the dir /ReverseFasta/ReverseAbridged where build.xml is located

  # Run the cmd $ ant run

  # You should get Build successful and output files are created under /resources/output/..

  # Now to test this application, run the cmd $ ant test


