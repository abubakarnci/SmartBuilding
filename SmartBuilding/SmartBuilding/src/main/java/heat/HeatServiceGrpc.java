package heat;

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
    comments = "Source: heat.proto")
public final class HeatServiceGrpc {

  private HeatServiceGrpc() {}

  public static final String SERVICE_NAME = "heat.HeatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<heat.HeatRequest,
      heat.HeatResponse> getHeatSwitchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "heatSwitch",
      requestType = heat.HeatRequest.class,
      responseType = heat.HeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<heat.HeatRequest,
      heat.HeatResponse> getHeatSwitchMethod() {
    io.grpc.MethodDescriptor<heat.HeatRequest, heat.HeatResponse> getHeatSwitchMethod;
    if ((getHeatSwitchMethod = HeatServiceGrpc.getHeatSwitchMethod) == null) {
      synchronized (HeatServiceGrpc.class) {
        if ((getHeatSwitchMethod = HeatServiceGrpc.getHeatSwitchMethod) == null) {
          HeatServiceGrpc.getHeatSwitchMethod = getHeatSwitchMethod = 
              io.grpc.MethodDescriptor.<heat.HeatRequest, heat.HeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "heat.HeatService", "heatSwitch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  heat.HeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  heat.HeatResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HeatServiceMethodDescriptorSupplier("heatSwitch"))
                  .build();
          }
        }
     }
     return getHeatSwitchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<heat.TemperatureRequest,
      heat.TemperatureResponse> getChangeTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeTemperature",
      requestType = heat.TemperatureRequest.class,
      responseType = heat.TemperatureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<heat.TemperatureRequest,
      heat.TemperatureResponse> getChangeTemperatureMethod() {
    io.grpc.MethodDescriptor<heat.TemperatureRequest, heat.TemperatureResponse> getChangeTemperatureMethod;
    if ((getChangeTemperatureMethod = HeatServiceGrpc.getChangeTemperatureMethod) == null) {
      synchronized (HeatServiceGrpc.class) {
        if ((getChangeTemperatureMethod = HeatServiceGrpc.getChangeTemperatureMethod) == null) {
          HeatServiceGrpc.getChangeTemperatureMethod = getChangeTemperatureMethod = 
              io.grpc.MethodDescriptor.<heat.TemperatureRequest, heat.TemperatureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "heat.HeatService", "changeTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  heat.TemperatureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  heat.TemperatureResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HeatServiceMethodDescriptorSupplier("changeTemperature"))
                  .build();
          }
        }
     }
     return getChangeTemperatureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HeatServiceStub newStub(io.grpc.Channel channel) {
    return new HeatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HeatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HeatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HeatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HeatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HeatServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void heatSwitch(heat.HeatRequest request,
        io.grpc.stub.StreamObserver<heat.HeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHeatSwitchMethod(), responseObserver);
    }

    /**
     * <pre>
     *Server side streaming 
     * </pre>
     */
    public void changeTemperature(heat.TemperatureRequest request,
        io.grpc.stub.StreamObserver<heat.TemperatureResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeTemperatureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHeatSwitchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                heat.HeatRequest,
                heat.HeatResponse>(
                  this, METHODID_HEAT_SWITCH)))
          .addMethod(
            getChangeTemperatureMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                heat.TemperatureRequest,
                heat.TemperatureResponse>(
                  this, METHODID_CHANGE_TEMPERATURE)))
          .build();
    }
  }

  /**
   */
  public static final class HeatServiceStub extends io.grpc.stub.AbstractStub<HeatServiceStub> {
    private HeatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeatServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public void heatSwitch(heat.HeatRequest request,
        io.grpc.stub.StreamObserver<heat.HeatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHeatSwitchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Server side streaming 
     * </pre>
     */
    public void changeTemperature(heat.TemperatureRequest request,
        io.grpc.stub.StreamObserver<heat.TemperatureResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getChangeTemperatureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HeatServiceBlockingStub extends io.grpc.stub.AbstractStub<HeatServiceBlockingStub> {
    private HeatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeatServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public heat.HeatResponse heatSwitch(heat.HeatRequest request) {
      return blockingUnaryCall(
          getChannel(), getHeatSwitchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Server side streaming 
     * </pre>
     */
    public java.util.Iterator<heat.TemperatureResponse> changeTemperature(
        heat.TemperatureRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getChangeTemperatureMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HeatServiceFutureStub extends io.grpc.stub.AbstractStub<HeatServiceFutureStub> {
    private HeatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeatServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<heat.HeatResponse> heatSwitch(
        heat.HeatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHeatSwitchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HEAT_SWITCH = 0;
  private static final int METHODID_CHANGE_TEMPERATURE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HeatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HeatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEAT_SWITCH:
          serviceImpl.heatSwitch((heat.HeatRequest) request,
              (io.grpc.stub.StreamObserver<heat.HeatResponse>) responseObserver);
          break;
        case METHODID_CHANGE_TEMPERATURE:
          serviceImpl.changeTemperature((heat.TemperatureRequest) request,
              (io.grpc.stub.StreamObserver<heat.TemperatureResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HeatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return heat.HeatServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HeatService");
    }
  }

  private static final class HeatServiceFileDescriptorSupplier
      extends HeatServiceBaseDescriptorSupplier {
    HeatServiceFileDescriptorSupplier() {}
  }

  private static final class HeatServiceMethodDescriptorSupplier
      extends HeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HeatServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (HeatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HeatServiceFileDescriptorSupplier())
              .addMethod(getHeatSwitchMethod())
              .addMethod(getChangeTemperatureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
