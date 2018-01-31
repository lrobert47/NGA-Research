import os
import sys

#opens file and reads out the metadata from the raw file
idfile = open("Surat_Videos_Fire_Metadata_raw.txt", "r")
#opens a new file to write to
outfile = open("Surat_Videos_Fire_Metadata.txt", "w")
for aline in idfile:
    #splits the file into an array
    meta = aline.split("{")
    #writes gernal data and thumbnails
    outfile.write(meta[3]+meta[8])
    #spaces the data out
    outfile.write("\n")

idfile.close()
outfile.close()
