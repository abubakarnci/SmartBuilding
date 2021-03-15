from zeroconf import Zeroconf
from zeroconf import ZeroconfServiceTypes
from zeroconf import ServiceBrowser


class Listener:

    def remove_service(self, zeroconf, serviceType, name):
        info = zeroconf.get_service_info(serviceType, name)

        print("Service Removed: " + info.name)

    def add_service(self, zeroconf, serviceType, name):
        info = zeroconf.get_service_info(serviceType, name)

        print("Address: " + str(info.parsed_addresses()))
        print("Port: " + str(info.port))
        print("Service Name: " + info.name)
        print("Server: " + info.server)
        print("Properties: " + str(info.properties))



zconf = Zeroconf()

serviceListener = Listener()

browser = ServiceBrowser(zconf, "_http._tcp.local.", serviceListener)

try:
    input("Press enter to exit...\n\n")
finally:
    zconf.close()



print('\n'.join(ZeroconfServiceTypes.find()))