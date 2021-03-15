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



def make_access(inputNam, inputId):
    return security_pb2.AccessRequest(name=inputNam, id=inputId)



def generate_access():
    staff = [
        make_access("Sam","x198774588"),
        make_access("Lisa","x178997544"),
        make_access("Jay","x18459759"),
        make_access("John","x16598745"),
        make_access("Shiobhan","x18759785"),
        make_access("Umer","x17268745"),
        make_access("Muhammad","x17112044"),
        make_access("Mark","x19872563")]

    print("Sending names to server")
    for x in staff:
        print(x)
        time.sleep(1)
        yield x

def grant_access(stub):

    response= stub.grantAccess(generate_access())
    print(response.reply)








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

        time.sleep(2)

        #sever side streaming
        print("-------------- Sever Side Streaming --------------")
        list_staff(stub)

        # Client side streaming
        print("-------------- Client Side Streaming --------------")
        grant_access(stub)


if __name__ == '__main__':
    logging.basicConfig()
    run()