#!/bin/sh
find . -name '*' -type f -exec chmod 644 {} \;
find . -name '*' -type d -exec chmod 755 {} \;
chmod 755 .
chmod 744 Bomberman clean install rights.sh