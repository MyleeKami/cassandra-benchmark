for I in $(seq 1 20); do curl -XGET 'http://127.0.0.1:8080/ponies/generate?nb=50000&insert=true' ; sleep 1 ; done
