#!/bin/sh

IATI_SCHEMA=iati-activities-schema.xsd
IDML_SCHEMA=test-data/IDML2.0.xsd
TEST_FILES="test-data/1.xml"
TEMP=/store/amp/docs/iati/data_example/iati-activities-schema-20100427/a.xml

for file in $TEST_FILES; do

  echo "****************************************************************"
  echo "  Testing file $file"
  echo "****************************************************************\n"

  echo "Validating IATI file against schema ..."
  xmllint --noout --schema $IATI_SCHEMA $file
  echo "done"

  echo "\nTransforming from IATI to IDML ..."
  xalan -in $file -out $TEMP -xsl iati2idml.xslt
  echo "done"

  echo "\nValidating IDML output against schema ..."
  xmllint --noout --schema $IDML_SCHEMA $TEMP
  echo "done\n"

  # xmllint --format $TEMP | less

  # rm -f $TEMP

done
