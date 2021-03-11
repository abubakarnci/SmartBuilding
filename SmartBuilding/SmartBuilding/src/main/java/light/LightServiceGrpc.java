package light;

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
    comments = "Source: light.proto")
public final class LightServiceGrpc {

  private LightServiceGrpc() {}

  public static final String SERVICE_NAME = "light.LightService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<light.PowerRequest,
      light.PowerResponse> getPowerSwitchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "powerSwitch",
      requestType = light.PowerRequest.class,
      responseType = light.PowerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<light.PowerRequest,
      light.PowerResponse> getPowerSwitchMethod() {
    io.grpc.MethodDescriptor<light.PowerRequest, light.PowerResponse> getPowerSwitchMethod;
    if ((getPowerSwitchMethod = LightServiceGrpc.getPowerSwitchMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getPowerSwitchMethod = LightServiceGrpc.getPowerSwitchMethod) == null) {
          LightServiceGrpc.getPowerSwitchMethod = getPowerSwitchMethod = 
              io.grpc.MethodDescriptor.<light.PowerRequest, light.PowerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "light.LightService", "powerSwitch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.PowerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.PowerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("powerSwitch"))
                  .build();
          }
        }
     }
     return getPowerSwitchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<light.ColourRequest,
      light.ColourResponse> getChangeColourMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeColour",
      requestType = light.ColourRequest.class,
      responseType = light.ColourResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<light.ColourRequest,
      light.ColourResponse> getChangeColourMethod() {
    io.grpc.MethodDescriptor<light.ColourRequest, light.ColourResponse> getChangeColourMethod;
    if ((getChangeColourMethod = LightServiceGrpc.getChangeColourMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getChangeColourMethod = LightServiceGrpc.getChangeColourMethod) == null) {
          LightServiceGrpc.getChangeColourMethod = getChangeColourMethod = 
              io.grpc.MethodDescriptor.<light.ColourRequest, light.ColourResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "light.LightService", "changeColour"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.ColourRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.ColourResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("changeColour"))
                  .build();
          }
        }
     }
     return getChangeColourMethod;
  }

  private static volatile io.grpc.MethodDescriptor<light.BillRequest,
      light.BillResponse> getCalculateBillMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calculateBill",
      requestType = light.BillRequest.class,
      responseType = light.BillResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<light.BillRequest,
      light.BillResponse> getCalculateBillMethod() {
    io.grpc.MethodDescriptor<light.BillRequest, light.BillResponse> getCalculateBillMethod;
    if ((getCalculateBillMethod = LightServiceGrpc.getCalculateBillMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getCalculateBillMethod = LightServiceGrpc.getCalculateBillMethod) == null) {
          LightServiceGrpc.getCalculateBillMethod = getCalculateBillMethod = 
              io.grpc.MethodDescriptor.<light.BillRequest, light.BillResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "light.LightService", "calculateBill"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.BillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  light.BillResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("calculateBill"))
                  .build();
          }
        }
     }
     return getCalculateBillMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LightServiceStub newStub(io.grpc.Channel channel) {
    return new LightServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LightServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LightServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LightServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LightServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LightServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void powerSwitch(light.PowerRequest request,
        io.grpc.stub.StreamObserver<light.PowerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPowerSwitchMethod(), responseObserver);
    }

    /**
     * <pre>
     *Client side streaming 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<light.ColourRequest> changeColour(
        io.grpc.stub.StreamObserver<light.ColourResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getChangeColourMethod(), responseObserver);
    }

    /**
     * <pre>
     *Bidirectional streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<light.BillRequest> calculateBill(
        io.grpc.stub.StreamObserver<light.BillResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCalculateBillMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPowerSwitchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                light.PowerRequest,
                light.PowerResponse>(
                  this, METHODID_POWER_SWITCH)))
          .addMethod(
            getChangeColourMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                light.ColourRequest,
                light.ColourResponse>(
                  this, METHODID_CHANGE_COLOUR)))
          .addMethod(
            getCalculateBillMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                light.BillRequest,
                light.BillResponse>(
                  this, METHODID_CALCULATE_BILL)))
          .build();
    }
  }

  /**
   */
  public static final class LightServiceStub extends io.grpc.stub.AbstractStub<LightServiceStub> {
    private LightServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void powerSwitch(light.PowerRequest request,
        io.grpc.stub.StreamObserver<light.PowerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPowerSwitchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Client side streaming 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<light.ColourRequest> changeColour(
        io.grpc.stub.StreamObserver<light.ColourResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getChangeColourMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *Bidirectional streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<light.BillRequest> calculateBill(
        io.grpc.stub.StreamObserver<light.BillResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCalculateBillMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class LightServiceBlockingStub extends io.grpc.stub.AbstractStub<LightServiceBlockingStub> {
    private LightServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public light.PowerResponse powerSwitch(light.PowerRequest request) {
      return blockingUnaryCall(
          getChannel(), getPowerSwitchMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LightServiceFutureStub extends io.grpc.stub.AbstractStub<LightServiceFutureStub> {
    private LightServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<light.PowerResponse> powerSwitch(
        light.PowerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPowerSwitchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_POWER_SWITCH = 0;
  private static final int METHODID_CHANGE_COLOUR = 1;
  private static final int METHODID_CALCULATE_BILL = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LightServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LightServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_POWER_SWITCH:
          serviceImpl.powerSwitch((light.PowerRequest) request,
              (io.grpc.stub.StreamObserver<light.PowerResponse>) responseObserver);
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
        case METHODID_CHANGE_COLOUR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.changeColour(
              (io.grpc.stub.StreamObserver<light.ColourResponse>) responseObserver);
        case METHODID_CALCULATE_BILL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.calculateBill(
              (io.grpc.stub.StreamObserver<light.BillResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LightServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return light.LightServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LightService");
    }
  }

  private static final class LightServiceFileDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier {
    LightServiceFileDescriptorSupplier() {}
  }

  private static final class LightServiceMethodDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LightServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LightServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LightServiceFileDescriptorSupplier())
              .addMethod(getPowerSwitchMethod())
              .addMethod(getChangeColourMethod())
              .addMethod(getCalculateBillMethod())
              .build();
        }
      }
    }
    return result;
  }
}
