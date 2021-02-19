package com.monsanto.arch.awsutil.identitymanagement

import akka.Done
import akka.actor.ActorSystem
import com.monsanto.arch.awsutil.AsyncAwsClient
import com.monsanto.arch.awsutil.auth.policy.Policy
import com.monsanto.arch.awsutil.identitymanagement.model._

import scala.concurrent.Future

trait AsyncIdentityManagementClient extends AsyncAwsClient {
  /** Creates a new role.
    *
    * @param roleName the name of the new role
    * @param assumeRolePolicy the trust relationship policy that grants an entity permission to assume the role (a JSON
    *                         document)
    */
  def createRole(roleName: String, assumeRolePolicy: Policy)(implicit as: ActorSystem): Future[Role]

  /** Creates a new role.
    *
    * @param roleName the name of the new role
    * @param assumeRolePolicy the trust relationship policy that grants an entity permission to assume the role (a JSON
    *                         document)
    * @param path an optional path to the role
    */
  def createRole(roleName: String, assumeRolePolicy: Policy, path: Path)(implicit as: ActorSystem): Future[Role]

  /** Deletes the role with the given name.  The role must not have any policies attached. */
  def deleteRole(roleName: String)(implicit as: ActorSystem): Future[Done]

  /** Lists all roles. */
  def listRoles()(implicit as: ActorSystem): Future[Seq[Role]]

  /** Lists all roles with the given path prefix. */
  def listRoles(prefix: Path)(implicit as: ActorSystem): Future[Seq[Role]]

  /** Attaches the managed policy to a role. */
  def attachRolePolicy(roleName: String, policyArn: PolicyArn)(implicit as: ActorSystem): Future[Done]

  /** Detaches the managed policy from a role. */
  def detachRolePolicy(roleName: String, policyArn: PolicyArn)(implicit as: ActorSystem): Future[Done]

  /** Lists all managed policies attached to the given role. */
  def listAttachedRolePolicies(roleName: String)(implicit as: ActorSystem): Future[Seq[AttachedPolicy]]

  /** Lists all managed policies attached to the given role that match the given path prefix. */
  def listAttachedRolePolicies(roleName: String, prefix: Path)
                              (implicit as: ActorSystem): Future[Seq[AttachedPolicy]]

  /* Gets the information for the current IAM user. */
  def getCurrentUser()(implicit as: ActorSystem): Future[User]

  /* Gets the information for the IAM user with the given name. */
  def getUser(name: String)(implicit as: ActorSystem): Future[User]

  /** Creates a new managed policy.
    *
    * @param name the name for the new managed policy
    * @param document the policy which will become the value for the managed policy
    */
  def createPolicy(name: String, document: Policy)
                  (implicit as: ActorSystem): Future[ManagedPolicy]

  /** Creates a new managed policy.
    *
    * @param name the name for the new managed policy
    * @param document the policy which will become the value for the managed policy
    * @param description a user-friendly description for the managed policy
    */
  def createPolicy(name: String, document: Policy, description: String)
                  (implicit as: ActorSystem): Future[ManagedPolicy]

  /** Creates a new managed policy.
    *
    * @param name the name for the new managed policy
    * @param document the policy which will become the value for the managed policy
    * @param description a user-friendly description for the managed policy
    * @param path the path at which to place the managed policy
    */
  def createPolicy(name: String, document: Policy, description: String, path: Path)
                  (implicit as: ActorSystem): Future[ManagedPolicy]

  /** Deletes the managed policy.
    *
    * @param policyArn the ARN of the policy to delete
    */
  def deletePolicy(policyArn: PolicyArn)(implicit as: ActorSystem): Future[Done]

  /** Retrieves information about the managed policy identified by the given ARN.
    *
    * @param policyArn the ARN of the managed policy to retrieve
    */
  def getPolicy(policyArn: PolicyArn)(implicit as: ActorSystem): Future[ManagedPolicy]

  /** Returns information about all managed policies. */
  def listPolicies()(implicit as: ActorSystem): Future[Seq[ManagedPolicy]]

  /** Returns information about all managed policies that match the given prefix. */
  def listPolicies(prefix: Path)(implicit as: ActorSystem): Future[Seq[ManagedPolicy]]

  /** Returns a list of policies matching the given request. */
  def listPolicies(request: ListPoliciesRequest)(implicit as: ActorSystem): Future[Seq[ManagedPolicy]]

  /** Returns information about all customer-managed policies. */
  def listLocalPolicies()(implicit as: ActorSystem): Future[Seq[ManagedPolicy]]

  /** Creates a new version of a managed policy.
    *
    * @param arn the ARN of the managed policy to update
    * @param document the policy content that should be used for the new version of the managed policy
    * @param setAsDefault specifies whether to set this version as the policy’s default version
    */
  def createPolicyVersion(arn: PolicyArn,
                          document: Policy,
                          setAsDefault: Boolean)
                         (implicit as: ActorSystem): Future[ManagedPolicyVersion]

  /** Deletes the specified version of a managed policy.
    *
    * @param arn       the ARN of the policy from which to delete a version
    * @param versionId the policy version to delete
    */
  def deletePolicyVersion(arn: PolicyArn, versionId: String)(implicit as: ActorSystem): Future[Done]

  /** Retrieves the specified version of a managed policy.
    *
    * @param arn       the ARN of the policy from which to retrieve a version
    * @param versionId the policy version to retrieve
    */
  def getPolicyVersion(arn: PolicyArn, versionId: String)(implicit as: ActorSystem): Future[ManagedPolicyVersion]

  /** Lists all of the versions of the given policy.
    *
    * @param arn the ARN of the policy for which to list versions
    */
  def listPolicyVersions(arn: PolicyArn)(implicit as: ActorSystem): Future[Seq[ManagedPolicyVersion]]

  /** Sets the specified version of the specified policy as the policy’s default (operative) version.
    *
    * @param arn       the ARN of the policy whose default version to set
    * @param versionId the policy version to set as the default (operative) version
    */
  def setDefaultPolicyVersion(arn: PolicyArn, versionId: String)(implicit as: ActorSystem): Future[Done]
}
