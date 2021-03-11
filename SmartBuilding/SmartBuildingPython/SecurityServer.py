import logging
from concurrent import futures
import time
import grpc

import security_pb2
import security_pb2_grpc


class Security(security_pb2_grpc.SecurityServiceServicer):
    def securitySwitch(self, request, context):
        print("Server received request: "+request.security)
        time.sleep(2)
        print("Security is turning ON")
        return security_pb2.SecurityResponse(security="Security is turned ON")


    def Liststaff(self, request, context):
        print("Server received request: " + request.ask)

        staff = ["Sam", "Lisa", "Jay", "John", "Shiobhan", "Umer", "Muhammad", "Mark"]
        for x in staff:
            #print(x)
            yield security_pb2.ListResponse(ans=x)
            time.sleep(2)

    def grantAccess (self, request, context):
        print("Server received Access request")

        return security_pb2.AccessResponse(reply="Access Granted")




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