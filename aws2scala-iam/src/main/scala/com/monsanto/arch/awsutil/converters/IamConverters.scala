package com.monsanto.arch.awsutil.converters

import java.net.URLDecoder

import com.amazonaws.services.identitymanagement.{model ⇒ aws}
import com.monsanto.arch.awsutil.auth.policy.Policy
import com.monsanto.arch.awsutil.identitymanagement.model._

/** Provides converters between ''aws2scala-iam'' objects and their AWS Java SDK counterparts. */
object IamConverters {
  implicit class AwsAttachRolePolicyRequest(val request: aws.AttachRolePolicyRequest) extends AnyVal {
    def asScala: AttachRolePolicyRequest =
      AttachRolePolicyRequest(request.getRoleName, PolicyArn.fromArnString(request.getPolicyArn))
  }

  implicit class ScalaAttachRolePolicyRequest(val request: AttachRolePolicyRequest) extends AnyVal {
    def asAws: aws.AttachRolePolicyRequest =
      new aws.AttachRolePolicyRequest()
        .withRoleName(request.roleName)
        .withPolicyArn(request.policyArn.arnString)
  }

  implicit class AwsAttachedPolicy(val attachedPolicy: aws.AttachedPolicy) extends AnyVal {
    def asScala: AttachedPolicy =
      AttachedPolicy(PolicyArn.fromArnString(attachedPolicy.getPolicyArn), attachedPolicy.getPolicyName)
  }

  implicit class ScalaAttachedPolicy(val attachedPolicy: AttachedPolicy) extends AnyVal {
    def asAws: aws.AttachedPolicy =
      new aws.AttachedPolicy()
        .withPolicyArn(attachedPolicy.arn.arnString)
        .withPolicyName(attachedPolicy.name)
  }

  implicit class AwsCreatePolicyRequest(val request: aws.CreatePolicyRequest) extends AnyVal {
    def asScala: CreatePolicyRequest =
      CreatePolicyRequest(
        request.getPolicyName,
        Policy.fromJson(request.getPolicyDocument),
        Option(request.getDescription),
        Option(request.getPath).map(Path.fromPathString(_)).getOrElse(Path.empty))
  }

  implicit class ScalaCreatePolicyRequest(val request: CreatePolicyRequest) extends AnyVal {
    def asAws: aws.CreatePolicyRequest =
      new aws.CreatePolicyRequest()
        .withDescription(request.description.orNull)
        .withPath(request.path.pathString)
        .withPolicyDocument(request.document.toJson)
        .withPolicyName(request.name)
  }

  implicit class ScalaCreatePolicyVersionRequest(val request: CreatePolicyVersionRequest) extends AnyVal {
    def asAws: aws.CreatePolicyVersionRequest =
      new aws.CreatePolicyVersionRequest()
        .withPolicyArn(request.arn.arnString)
        .withPolicyDocument(request.document.toJson)
        .withSetAsDefault(java.lang.Boolean.valueOf(request.setAsDefault))
  }

  implicit class AwsCreateRoleRequest(val request: aws.CreateRoleRequest) extends AnyVal {
    def asScala: CreateRoleRequest =
      CreateRoleRequest(
        request.getRoleName,
        Policy.fromJson(request.getAssumeRolePolicyDocument),
        Option(request.getPath).map(Path.fromPathString(_)))
  }

  implicit class ScalaCreateRoleRequest(val request: CreateRoleRequest) extends AnyVal {
    def asAws: aws.CreateRoleRequest = {
      val awsRequest = new aws.CreateRoleRequest()
        .withRoleName(request.name)
        .withAssumeRolePolicyDocument(request.assumeRolePolicy.toJson)
      request.path.foreach(p ⇒ awsRequest.setPath(p.pathString))
      awsRequest
    }
  }

  implicit class AwsDetachRolePolicyRequest(val request: aws.DetachRolePolicyRequest) extends AnyVal {
    def asScala: DetachRolePolicyRequest =
      DetachRolePolicyRequest(request.getRoleName, PolicyArn.fromArnString(request.getPolicyArn))
  }

  implicit class ScalaDeletePolicyVersionRequest(val request: DeletePolicyVersionRequest) extends AnyVal {
    def asAws: aws.DeletePolicyVersionRequest =
      new aws.DeletePolicyVersionRequest()
        .withPolicyArn(request.arn.arnString)
        .withVersionId(request.versionId)
  }

  implicit class ScalaDetachRolePolicyRequest(val request: DetachRolePolicyRequest) extends AnyVal {
    def asAws: aws.DetachRolePolicyRequest =
      new aws.DetachRolePolicyRequest()
        .withRoleName(request.roleName)
        .withPolicyArn(request.policyArn.arnString)
  }

  implicit class ScalaGetPolicyVersionRequest(val request: GetPolicyVersionRequest) extends AnyVal {
    def asAws: aws.GetPolicyVersionRequest =
      new aws.GetPolicyVersionRequest()
        .withPolicyArn(request.arn.arnString)
        .withVersionId(request.versionId)
  }

  implicit class ScalaGetUserRequest(val request: GetUserRequest) extends AnyVal {
    def asAws: aws.GetUserRequest = {
      val awsRequest = new aws.GetUserRequest
      request.userName.foreach(u ⇒ awsRequest.setUserName(u))
      awsRequest
    }
  }

  implicit class ScalaListAttachedRolePoliciesRequest(val request: ListAttachedRolePoliciesRequest) extends AnyVal {
    def asAws: aws.ListAttachedRolePoliciesRequest = {
      val awsRequest = new aws.ListAttachedRolePoliciesRequest().withRoleName(request.roleName)
      request.prefix.foreach(p ⇒ awsRequest.setPathPrefix(p.pathString))
      awsRequest
    }
  }

  implicit class ScalaListPoliciesRequest(val request: ListPoliciesRequest) extends AnyVal {
    def asAws: aws.ListPoliciesRequest =
      new aws.ListPoliciesRequest()
        .withOnlyAttached(java.lang.Boolean.valueOf(request.onlyAttached))
        .withPathPrefix(request.prefix.pathString)
        .withScope(request.scope.name)
  }

  implicit class ScalaListRolesRequest(val request: ListRolesRequest) extends AnyVal {
    def asAws: aws.ListRolesRequest = {
      val awsRequest = new aws.ListRolesRequest
      request.prefix.foreach(p ⇒ awsRequest.setPathPrefix(p.pathString))
      awsRequest
    }
  }

  implicit class AwsIamPolicy(val policy: aws.Policy) extends AnyVal {
    def asScala: ManagedPolicy =
      ManagedPolicy(
        policy.getPolicyName,
        policy.getPolicyId,
        PolicyArn.fromArnString(policy.getArn),
        Path.fromPathString(policy.getPath),
        policy.getDefaultVersionId,
        policy.getAttachmentCount.toInt,
        policy.getIsAttachable.booleanValue(),
        Option(policy.getDescription),
        policy.getCreateDate,
        policy.getUpdateDate)
  }

  implicit class ScalaIamPolicy(val policy: ManagedPolicy) extends AnyVal {
    def asAws: aws.Policy =
      new aws.Policy()
        .withPolicyName(policy.name)
        .withPolicyId(policy.id)
        .withArn(policy.arn.arnString)
        .withPath(policy.path.pathString)
        .withDefaultVersionId(policy.defaultVersionId)
        .withAttachmentCount(Integer.valueOf(policy.attachmentCount))
        .withIsAttachable(java.lang.Boolean.valueOf(policy.attachable))
        .withDescription(policy.description.orNull)
        .withCreateDate(policy.created)
        .withUpdateDate(policy.updated)
  }

  implicit class AwsPolicyScopeType(val scopeType: aws.PolicyScopeType) extends AnyVal {
    def asScala: ListPoliciesRequest.Scope =
      scopeType match {
        case aws.PolicyScopeType.All   ⇒ ListPoliciesRequest.Scope.All
        case aws.PolicyScopeType.AWS   ⇒ ListPoliciesRequest.Scope.AWS
        case aws.PolicyScopeType.Local ⇒ ListPoliciesRequest.Scope.Local
      }
  }

  implicit class ScalaPolicyScopeType(val scopeType: ListPoliciesRequest.Scope) extends AnyVal {
    def asAws: aws.PolicyScopeType =
      scopeType match {
        case ListPoliciesRequest.Scope.All   ⇒ aws.PolicyScopeType.All
        case ListPoliciesRequest.Scope.AWS   ⇒ aws.PolicyScopeType.AWS
        case ListPoliciesRequest.Scope.Local ⇒ aws.PolicyScopeType.Local
      }
  }

  implicit class AwsPolicyVersion(val policyVersion: aws.PolicyVersion) extends AnyVal {
    def asScala: ManagedPolicyVersion =
      ManagedPolicyVersion(
        Option(policyVersion.getDocument).map(d ⇒ Policy.fromJson(urlDecodeJson(d))),
        policyVersion.getVersionId,
        policyVersion.getIsDefaultVersion.booleanValue(),
        policyVersion.getCreateDate)
  }

  implicit class ScalaPolicyVersion(val policyVersion: ManagedPolicyVersion) extends AnyVal {
    def asAws: aws.PolicyVersion =
      new aws.PolicyVersion()
        .withDocument(policyVersion.document.map(_.toJson).orNull)
        .withVersionId(policyVersion.versionId)
        .withIsDefaultVersion(java.lang.Boolean.valueOf(policyVersion.isDefaultVersion))
        .withCreateDate(policyVersion.created)
  }

  implicit class AwsRole(val role: aws.Role) extends AnyVal {
    def asScala: Role =
      Role(
        RoleArn.fromArnString(role.getArn),
        role.getRoleName,
        Path.fromPathString(role.getPath),
        role.getRoleId,
        Policy.fromJson(urlDecodeJson(role.getAssumeRolePolicyDocument)),
        role.getCreateDate)
  }

  implicit class ScalaRole(val role: Role) extends AnyVal {
    def asAws: aws.Role =
      new aws.Role()
        .withArn(role.arn.arnString)
        .withRoleName(role.name)
        .withPath(role.path.pathString)
        .withRoleId(role.id)
        .withAssumeRolePolicyDocument(role.assumeRolePolicyDocument.toJson)
        .withCreateDate(role.created)
  }

  implicit class ScalaSetDefaultPolicyVersionRequest(val request: SetDefaultPolicyVersionRequest) extends AnyVal {
    def asAws: aws.SetDefaultPolicyVersionRequest =
      new aws.SetDefaultPolicyVersionRequest()
        .withPolicyArn(request.arn.arnString)
        .withVersionId(request.versionId)
  }

  implicit class AwsUser(val user: aws.User) extends AnyVal {
    def asScala: User =
      User(
        Path.fromPathString(user.getPath),
        user.getUserName,
        user.getUserId,
        UserArn.fromArnString(user.getArn),
        user.getCreateDate,
        Option(user.getPasswordLastUsed))
  }

  implicit class ScalaUser(val user: User) extends AnyVal {
    def asAws: aws.User = {
      val awsUser = new aws.User(user.path.pathString, user.name, user.id, user.arn.arnString, user.created)
      user.passwordLastUsed.foreach(d ⇒ awsUser.setPasswordLastUsed(d))
      awsUser
    }
  }

  private def urlDecodeJson(rawString: String): String = {
    if (rawString.toLowerCase.startsWith("%7b")) {
      URLDecoder.decode(rawString, "UTF-8")
    } else {
      rawString
    }
  }
}
