package security;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: security.proto")
public final class SecurityServiceGrpc {

  private SecurityServiceGrpc() {}

  public static final String SERVICE_NAME = "security.SecurityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<security.SecurityRequest,
      security.SecurityResponse> getSecuritySwitchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "securitySwitch",
      requestType = security.SecurityRequest.class,
      responseType = security.SecurityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<security.SecurityRequest,
      security.SecurityResponse> getSecuritySwitchMethod() {
    io.grpc.MethodDescriptor<security.SecurityRequest, security.SecurityResponse> getSecuritySwitchMethod;
    if ((getSecuritySwitchMethod = SecurityServiceGrpc.getSecuritySwitchMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getSecuritySwitchMethod = SecurityServiceGrpc.getSecuritySwitchMethod) == null) {
          SecurityServiceGrpc.getSecuritySwitchMethod = getSecuritySwitchMethod = 
              io.grpc.MethodDescriptor.<security.SecurityRequest, security.SecurityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "security.SecurityService", "securitySwitch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.SecurityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.SecurityResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("securitySwitch"))
                  .build();
          }
        }
     }
     return getSecuritySwitchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<security.ListRequest,
      security.ListResponse> getListstaffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Liststaff",
      requestType = security.ListRequest.class,
      responseType = security.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<security.ListRequest,
      security.ListResponse> getListstaffMethod() {
    io.grpc.MethodDescriptor<security.ListRequest, security.ListResponse> getListstaffMethod;
    if ((getListstaffMethod = SecurityServiceGrpc.getListstaffMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getListstaffMethod = SecurityServiceGrpc.getListstaffMethod) == null) {
          SecurityServiceGrpc.getListstaffMethod = getListstaffMethod = 
              io.grpc.MethodDescriptor.<security.ListRequest, security.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "security.SecurityService", "Liststaff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.ListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.ListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("Liststaff"))
                  .build();
          }
        }
     }
     return getListstaffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<security.AccessRequest,
      security.AccessResponse> getGrantAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grantAccess",
      requestType = security.AccessRequest.class,
      responseType = security.AccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<security.AccessRequest,
      security.AccessResponse> getGrantAccessMethod() {
    io.grpc.MethodDescriptor<security.AccessRequest, security.AccessResponse> getGrantAccessMethod;
    if ((getGrantAccessMethod = SecurityServiceGrpc.getGrantAccessMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getGrantAccessMethod = SecurityServiceGrpc.getGrantAccessMethod) == null) {
          SecurityServiceGrpc.getGrantAccessMethod = getGrantAccessMethod = 
              io.grpc.MethodDescriptor.<security.AccessRequest, security.AccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "security.SecurityService", "grantAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.AccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  security.AccessResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("grantAccess"))
                  .build();
          }
        }
     }
     return getGrantAccessMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SecurityServiceStub newStub(io.grpc.Channel channel) {
    return new SecurityServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SecurityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SecurityServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SecurityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SecurityServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SecurityServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void securitySwitch(security.SecurityRequest request,
        io.grpc.stub.StreamObserver<security.SecurityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSecuritySwitchMethod(), responseObserver);
    }

    /**
     * <pre>
     *server side streaming
     * </pre>
     */
    public void liststaff(security.ListRequest request,
        io.grpc.stub.StreamObserver<security.ListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getListstaffMethod(), responseObserver);
    }

    /**
     * <pre>
     *client side streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<security.AccessRequest> grantAccess(
        io.grpc.stub.StreamObserver<security.AccessResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGrantAccessMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSecuritySwitchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                security.SecurityRequest,
                security.SecurityResponse>(
                  this, METHODID_SECURITY_SWITCH)))
          .addMethod(
            getListstaffMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                security.ListRequest,
                security.ListResponse>(
                  this, METHODID_LISTSTAFF)))
          .addMethod(
            getGrantAccessMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                security.AccessRequest,
                security.AccessResponse>(
                  this, METHODID_GRANT_ACCESS)))
          .build();
    }
  }

  /**
   */
  public static final class SecurityServiceStub extends io.grpc.stub.AbstractStub<SecurityServiceStub> {
    private SecurityServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void securitySwitch(security.SecurityRequest request,
        io.grpc.stub.StreamObserver<security.SecurityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSecuritySwitchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *server side streaming
     * </pre>
     */
    public void liststaff(security.ListRequest request,
        io.grpc.stub.StreamObserver<security.ListResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getListstaffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *client side streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<security.AccessRequest> grantAccess(
        io.grpc.stub.StreamObserver<security.AccessResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGrantAccessMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SecurityServiceBlockingStub extends io.grpc.stub.AbstractStub<SecurityServiceBlockingStub> {
    private SecurityServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public security.SecurityResponse securitySwitch(security.SecurityRequest request) {
      return blockingUnaryCall(
          getChannel(), getSecuritySwitchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *server side streaming
     * </pre>
     */
    public java.util.Iterator<security.ListResponse> liststaff(
        security.ListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getListstaffMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SecurityServiceFutureStub extends io.grpc.stub.AbstractStub<SecurityServiceFutureStub> {
    private SecurityServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<security.SecurityResponse> securitySwitch(
        security.SecurityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSecuritySwitchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SECURITY_SWITCH = 0;
  private static final int METHODID_LISTSTAFF = 1;
  private static final int METHODID_GRANT_ACCESS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SecurityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SecurityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SECURITY_SWITCH:
          serviceImpl.securitySwitch((security.SecurityRequest) request,
              (io.grpc.stub.StreamObserver<security.SecurityResponse>) responseObserver);
          break;
        case METHODID_LISTSTAFF:
          serviceImpl.liststaff((security.ListRequest) request,
              (io.grpc.stub.StreamObserver<security.ListResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GRANT_ACCESS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.grantAccess(
              (io.grpc.stub.StreamObserver<security.AccessResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SecurityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return security.SecurityServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SecurityService");
    }
  }

  private static final class SecurityServiceFileDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier {
    SecurityServiceFileDescriptorSupplier() {}
  }

  private static final class SecurityServiceMethodDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SecurityServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SecurityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SecurityServiceFileDescriptorSupplier())
              .addMethod(getSecuritySwitchMethod())
              .addMethod(getListstaffMethod())
              .addMethod(getGrantAccessMethod())
              .build();
        }
      }
    }
    return result;
  }
}
