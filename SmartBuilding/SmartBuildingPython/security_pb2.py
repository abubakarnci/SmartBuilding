# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: security.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='security.proto',
  package='security',
  syntax='proto3',
  serialized_options=b'\n\010securityB\rSecurityProtoP\001\242\002\004SECU',
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x0esecurity.proto\x12\x08security\"#\n\x0fSecurityRequest\x12\x10\n\x08security\x18\x01 \x01(\x08\"$\n\x10SecurityResponse\x12\x10\n\x08security\x18\x01 \x01(\x08\"\x1a\n\x0bListRequest\x12\x0b\n\x03\x61sk\x18\x01 \x01(\t\"\x1b\n\x0cListResponse\x12\x0b\n\x03\x61ns\x18\x01 \x01(\t\")\n\rAccessRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\x12\n\n\x02id\x18\x02 \x01(\t\"\x1f\n\x0e\x41\x63\x63\x65ssResponse\x12\r\n\x05reply\x18\x01 \x01(\t2\xe2\x01\n\x0fSecurityService\x12I\n\x0esecuritySwitch\x12\x19.security.SecurityRequest\x1a\x1a.security.SecurityResponse\"\x00\x12>\n\tListstaff\x12\x15.security.ListRequest\x1a\x16.security.ListResponse\"\x00\x30\x01\x12\x44\n\x0bgrantAccess\x12\x17.security.AccessRequest\x1a\x18.security.AccessResponse\"\x00(\x01\x42\"\n\x08securityB\rSecurityProtoP\x01\xa2\x02\x04SECUb\x06proto3'
)




_SECURITYREQUEST = _descriptor.Descriptor(
  name='SecurityRequest',
  full_name='security.SecurityRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='security', full_name='security.SecurityRequest.security', index=0,
      number=1, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=28,
  serialized_end=63,
)


_SECURITYRESPONSE = _descriptor.Descriptor(
  name='SecurityResponse',
  full_name='security.SecurityResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='security', full_name='security.SecurityResponse.security', index=0,
      number=1, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=65,
  serialized_end=101,
)


_LISTREQUEST = _descriptor.Descriptor(
  name='ListRequest',
  full_name='security.ListRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='ask', full_name='security.ListRequest.ask', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=103,
  serialized_end=129,
)


_LISTRESPONSE = _descriptor.Descriptor(
  name='ListResponse',
  full_name='security.ListResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='ans', full_name='security.ListResponse.ans', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=131,
  serialized_end=158,
)


_ACCESSREQUEST = _descriptor.Descriptor(
  name='AccessRequest',
  full_name='security.AccessRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='name', full_name='security.AccessRequest.name', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='id', full_name='security.AccessRequest.id', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=160,
  serialized_end=201,
)


_ACCESSRESPONSE = _descriptor.Descriptor(
  name='AccessResponse',
  full_name='security.AccessResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='reply', full_name='security.AccessResponse.reply', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=203,
  serialized_end=234,
)

DESCRIPTOR.message_types_by_name['SecurityRequest'] = _SECURITYREQUEST
DESCRIPTOR.message_types_by_name['SecurityResponse'] = _SECURITYRESPONSE
DESCRIPTOR.message_types_by_name['ListRequest'] = _LISTREQUEST
DESCRIPTOR.message_types_by_name['ListResponse'] = _LISTRESPONSE
DESCRIPTOR.message_types_by_name['AccessRequest'] = _ACCESSREQUEST
DESCRIPTOR.message_types_by_name['AccessResponse'] = _ACCESSRESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

SecurityRequest = _reflection.GeneratedProtocolMessageType('SecurityRequest', (_message.Message,), {
  'DESCRIPTOR' : _SECURITYREQUEST,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.SecurityRequest)
  })
_sym_db.RegisterMessage(SecurityRequest)

SecurityResponse = _reflection.GeneratedProtocolMessageType('SecurityResponse', (_message.Message,), {
  'DESCRIPTOR' : _SECURITYRESPONSE,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.SecurityResponse)
  })
_sym_db.RegisterMessage(SecurityResponse)

ListRequest = _reflection.GeneratedProtocolMessageType('ListRequest', (_message.Message,), {
  'DESCRIPTOR' : _LISTREQUEST,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.ListRequest)
  })
_sym_db.RegisterMessage(ListRequest)

ListResponse = _reflection.GeneratedProtocolMessageType('ListResponse', (_message.Message,), {
  'DESCRIPTOR' : _LISTRESPONSE,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.ListResponse)
  })
_sym_db.RegisterMessage(ListResponse)

AccessRequest = _reflection.GeneratedProtocolMessageType('AccessRequest', (_message.Message,), {
  'DESCRIPTOR' : _ACCESSREQUEST,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.AccessRequest)
  })
_sym_db.RegisterMessage(AccessRequest)

AccessResponse = _reflection.GeneratedProtocolMessageType('AccessResponse', (_message.Message,), {
  'DESCRIPTOR' : _ACCESSRESPONSE,
  '__module__' : 'security_pb2'
  # @@protoc_insertion_point(class_scope:security.AccessResponse)
  })
_sym_db.RegisterMessage(AccessResponse)


DESCRIPTOR._options = None

_SECURITYSERVICE = _descriptor.ServiceDescriptor(
  name='SecurityService',
  full_name='security.SecurityService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=237,
  serialized_end=463,
  methods=[
  _descriptor.MethodDescriptor(
    name='securitySwitch',
    full_name='security.SecurityService.securitySwitch',
    index=0,
    containing_service=None,
    input_type=_SECURITYREQUEST,
    output_type=_SECURITYRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='Liststaff',
    full_name='security.SecurityService.Liststaff',
    index=1,
    containing_service=None,
    input_type=_LISTREQUEST,
    output_type=_LISTRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='grantAccess',
    full_name='security.SecurityService.grantAccess',
    index=2,
    containing_service=None,
    input_type=_ACCESSREQUEST,
    output_type=_ACCESSRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_SECURITYSERVICE)

DESCRIPTOR.services_by_name['SecurityService'] = _SECURITYSERVICE

# @@protoc_insertion_point(module_scope)
