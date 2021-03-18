import logging
from concurrent import futures
import time
import grpc

import security_pb2
import security_pb2_grpc


class Security(security_pb2_grpc.SecurityServiceServicer):
    def securitySwitch(self, request, context):
        print("Server received request: "+str(request.security))
        time.sleep(2)

        if request.security==True:
            print("Security is turning ON")
            return security_pb2.SecurityResponse(security=True)

        elif request.security==False:
            print("Security is turning OFF")
            return security_pb2.SecurityResponse(security=False)





    def Liststaff(self, request, context):
        print("Server received staff request: " + request.ask)

        staff = ["Sam", "Lisa", "Jay", "John", "Shiobhan", "Umer", "Muhammad", "Mark"]
        for x in staff:
            #print(x)
            yield security_pb2.ListResponse(ans=x)
            time.sleep(2)

    def grantAccess (self, request, context):

        for x in  request:
            print("Server received Access request for: "+x.name+"("+x.id+")")

        return security_pb2.AccessResponse(reply="Today's Access Granted to above names")





def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    security_pb2_grpc.add_SecurityServiceServicer_to_server(Security(), server)
    server.add_insecure_port('[::]:50053')
    print("Server started. Listening on port: 50053")
    server.start()
    server.wait_for_termination()

if __name__ == '__main__':
    logging.basicConfig()
    serve()