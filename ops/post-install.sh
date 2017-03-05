#!/bin/sh


if [ ! -f majanadmintools ]; then
    cp majanadmintools /etc/init.d/majanadmintools
    chmod +x /etc/init.d/majanadmintools -v
fi

service majanadmintools start
