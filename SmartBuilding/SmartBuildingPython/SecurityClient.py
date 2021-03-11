import grpc
import logging
import security_pb2
import security_pb2_grpc
import time


def list_staff(stub):
    print("Requesting staff present in office")

    features = stub.Liststaff(security_pb2.ListRequest(ask='How many people are in building'))
    print("Client received")
    for feature in features:
        print("Name: %s" % (feature.ans))


def grant_access(stub):
    print("Sending credentials to server")
    name="Sam"
    id="x17112044"
    password="1234567"

    staff = ["Sam", "Lisa", "Jay", "John", "Shiobhan", "Umer", "Muhammad", "Mark"]
    for x in staff:
        print(x)
        summ=stub.grantAccess(security_pb2.AccessRequest(name=x))
        time.sleep(0.5)
    print(summ.reply)





   # response=stub.grantAccess(security_pb2.AccessRequest(id=id))
    #response=stub.grantAccess(security_pb2.AccessRequest(password=password))




def run():
    with grpc.insecure_channel('localhost:50053') as channel:
        stub = security_pb2_grpc.SecurityServiceStub(channel)
        response = stub.securitySwitch(security_pb2.SecurityRequest(security='Turn ON security'))

        time.sleep(2)
        # unary
        print("-------------- Unary --------------")
        print("Client received: " + response.security)


        #sever side streaming
        print("-------------- Sever Side Streaming --------------")
        list_staff(stub)

        # Client side streaming
        print("-------------- Client Side Streaming --------------")
        grant_access(stub)


if __name__ == '__main__':
    logging.basicConfig()
    run()