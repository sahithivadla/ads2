import socket
import os
import mimetypes


class HTTPServer:
    def __init__(self, IP, port):
        super().__init__()
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM, socket.IPPROTO_TCP) as self.s:
            self.s.bind((IP, port))
            self.s.listen()
            while True:
                conn, addr = self.s.accept()
                count=0
                with conn:
                    print('Connected by', addr)
                    # TODO read the request and extract the URI
                    request = conn.recv(1024)
                    temp = request.decode().split(' ')
                    DOC_ROOT = "C:/Users/Sahithi/Desktop/tinywebserver/tws-m2/www/"
                    if(len(temp) > 1):
                       # TODO update the parameter with the request URI
                        if (count>2):
                           uri =  DOC_ROOT + temp[1].split("/")[1].replace(DOC_ROOT,"")
                        else:
                            uri = DOC_ROOT + temp[1].replace(DOC_ROOT,"")
                        count += 1
                        print (temp[1].split("/")[1])
                    else:
                       uri = "C:/Users/Sahithi/Desktop/tinywebserver/tws-m2/www/"
                       count+=1
                    code, c_type, c_length, data = self.get_data(uri)
                    response = self.response_headers(code, c_type, c_length).encode() + data
                    conn.sendall(response)
                    conn.close()

    def get_data(self, uri):
        '''
        TODO: This function has to be updated for M2
        '''
        print(uri+"=================================")
        if uri.split("/")[-1]=="favicon.ico":
            pass



        # if (uri == "C:/Users/Sahithi/Desktop/tinywebserver/tws-m2/www/"):
        #     data = "<h1>Welcome to our web server!</h1>"
        #     return 200, "text/html", len(data), data.encode()

        if (os.path.isdir(uri)):
            data = ""
            contents = []
            contents = os.listdir(uri)
            for i in contents:
                hreftags = uri+"/"+i
                data += "<a href = "+ hreftags + ">" +i+"</a> <br/>"
            return 200, "text/html", len(data), data.encode()

        DOC_ROOT1 = "C:/Users/Sahithi/Desktop/tinywebserver/tws-m2/www/"
        if(os.path.isfile(uri)):
            mime = mimetypes.MimeTypes().guess_type(uri)[0]
            in_file = open(uri , "rb")
            data = in_file.read()
            return 200, mime, len(data), data

        # elif(DOC_ROOT1 == uri):
        #     data = "<h1>Welcome to our web server!</h1>"
        #     return 200, "text/html", len(data), data.encode()

        data = "<h1>Sorry! the source you are searching for is not available.</h1>"
        return 404, "text/html", len(data), data.encode()


    def response_headers(self, status_code, content_type, length):
        line = "\n"

        # TODO update this dictionary for 404 status codes
        response_code = {200: "200 OK", 404:"404 Not Found"}

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
    HTTPServer('127.0.0.1', 7877)

if __name__ == "__main__":
    main()