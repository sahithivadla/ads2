import socket
import os
import mimetypes
from os import path


class HTTPServer:

    enable_dir_browsing = True
    path_seperator = os.sep
    document_root = "." + path_seperator + "www"

    def __init__(self, IP, port):

        super().__init__()

        with socket.socket(socket.AF_INET, socket.SOCK_STREAM, socket.IPPROTO_TCP) as self.s:

            self.s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)   # To use a existing port use socket.SO_REUSEADDR

            print ("Socket successfully created")

            self.s.bind((IP, port))

            print ("socket binded to %s" %(port))


            self.s.listen(5)

            print ("socket is listening")

            while True:

              clientsocket, address = self.s.accept()

              print ('Got connection from', address)

              with clientsocket :

                http_request = clientsocket.recv(1024).decode()
                code, c_type, c_length, data = self.get_data(http_request)
                http_response = self.response_headers(code, c_type, c_length).encode() + data
                clientsocket.sendall(http_response)

    def get_data(self, http_request):

        '''
            TODO: This function has to be updated for M3
        '''
        if self.enable_dir_browsing :

          uri = http_request.split(" ")
          uri = uri[1] if len(uri) > 1 else "/"

          print(uri)

          # If uri contains favicon return empty string in bytes format

          if uri.find("favicon") != -1 :
            return 200, "", len(""), "".encode()

          # If uri is equal to '/' then display the contents in document_root i.e www folder

          if uri == "/" :

            content = self.directory_listing(self.document_root, uri)
            content_type = "text/html"

            return 200, content_type, len(content), content.encode()

          # If uri is a directory the display all the contents in that directory

          if path.isdir(self.document_root + uri) :

            content = self.directory_listing(self.document_root + uri, uri)
            content_type = "text/html"

            return 200, content_type, len (content), content.encode()

          # If uri is a file then open that file

          if path.isfile("." + self.path_seperator + "www" + uri) :

            content_type = mimetypes.MimeTypes().guess_type("." + self.path_seperator + "www" + uri)[0]

            with open("." + self.path_seperator + "www" + uri, "rb") as f :
              content = f.read()

            return 200, content_type, len(content), content

          # If no such file or folder exists in document_root then display File Not Found Error Message

          content = "<h1>File Not Found</h1>"
          return 404, "text/html", len(content), content.encode()

        else:
          content = "<h1>Web Server Under Construction</h1>"
          return 200, "text/html", len(content), content.encode()


    def directory_listing(self, dir_path, uri):

        if uri == "/" :
          uri = ""

        resp = "<html><body>"

        with os.scandir(dir_path) as entries:
          for entry in entries:
            if not entry.name.startswith(".DS_Store") :
              resp = resp + "<a href='" + uri + self.path_seperator + entry.name + "'>" + entry.name + "</a></br>"

        return resp + "</body></html>"

    def response_headers(self, status_code, content_type, length):
        line = "\n"

        # TODO update this dictionary for 404 status codes
        response_code = {200: "200 OK", 404: "404 File Not Found"}

        headers = ""
        headers += "HTTP/1.1 " + response_code[status_code] + line
        headers += "Content-Type: " + content_type + line
        headers += "Content-Length: " + str(length) + line
        headers += "Connection: close" + line
        headers += line
        return headers

def main():
    # test harness checks for your web server on the localhost and on port 8888
    # do not change the host and port
    # you can change  the HTTPServer object if you are not following OOP
    HTTPServer('127.0.0.1', 8888)

if __name__ == "__main__":
    main()