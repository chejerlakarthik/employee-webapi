#!/bin/bash
echo "Deleting rows and dropping tables.."
sh clean_db.sh
echo "Deleting rows and dropping tables.."
sh init_db.sh
echo "Deleting rows and dropping tables.."
sh load_db.sh
echo "Done...Database is ready..."
