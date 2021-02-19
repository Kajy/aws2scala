package com.monsanto.arch.awsutil.ec2.model

import com.amazonaws.services.ec2.{model => aws}
import com.monsanto.arch.awsutil.util.{AwsEnumeration, AwsEnumerationCompanion}

sealed abstract class InstanceType(val toAws: aws.InstanceType) extends AwsEnumeration[aws.InstanceType]

object InstanceType extends AwsEnumerationCompanion[InstanceType, aws.InstanceType] {

  case object T1Micro extends InstanceType(aws.InstanceType.T1Micro)

  case object T2Nano extends InstanceType(aws.InstanceType.T2Nano)

  case object T2Micro extends InstanceType(aws.InstanceType.T2Micro)

  case object T2Small extends InstanceType(aws.InstanceType.T2Small)

  case object T2Medium extends InstanceType(aws.InstanceType.T2Medium)

  case object T2Large extends InstanceType(aws.InstanceType.T2Large)

  case object T2Xlarge extends InstanceType(aws.InstanceType.T2Xlarge)

  case object T22xlarge extends InstanceType(aws.InstanceType.T22xlarge)

  case object T3Nano extends InstanceType(aws.InstanceType.T3Nano)

  case object T3Micro extends InstanceType(aws.InstanceType.T3Micro)

  case object T3Small extends InstanceType(aws.InstanceType.T3Small)

  case object T3Medium extends InstanceType(aws.InstanceType.T3Medium)

  case object T3Large extends InstanceType(aws.InstanceType.T3Large)

  case object T3Xlarge extends InstanceType(aws.InstanceType.T3Xlarge)

  case object T32xlarge extends InstanceType(aws.InstanceType.T32xlarge)

  case object T3aNano extends InstanceType(aws.InstanceType.T3aNano)

  case object T3aMicro extends InstanceType(aws.InstanceType.T3aMicro)

  case object T3aSmall extends InstanceType(aws.InstanceType.T3aSmall)

  case object T3aMedium extends InstanceType(aws.InstanceType.T3aMedium)

  case object T3aLarge extends InstanceType(aws.InstanceType.T3aLarge)

  case object T3aXlarge extends InstanceType(aws.InstanceType.T3aXlarge)

  case object T3a2xlarge extends InstanceType(aws.InstanceType.T3a2xlarge)

  case object T4gNano extends InstanceType(aws.InstanceType.T4gNano)

  case object T4gMicro extends InstanceType(aws.InstanceType.T4gMicro)

  case object T4gSmall extends InstanceType(aws.InstanceType.T4gSmall)

  case object T4gMedium extends InstanceType(aws.InstanceType.T4gMedium)

  case object T4gLarge extends InstanceType(aws.InstanceType.T4gLarge)

  case object T4gXlarge extends InstanceType(aws.InstanceType.T4gXlarge)

  case object T4g2xlarge extends InstanceType(aws.InstanceType.T4g2xlarge)

  case object M1Small extends InstanceType(aws.InstanceType.M1Small)

  case object M1Medium extends InstanceType(aws.InstanceType.M1Medium)

  case object M1Large extends InstanceType(aws.InstanceType.M1Large)

  case object M1Xlarge extends InstanceType(aws.InstanceType.M1Xlarge)

  case object M3Medium extends InstanceType(aws.InstanceType.M3Medium)

  case object M3Large extends InstanceType(aws.InstanceType.M3Large)

  case object M3Xlarge extends InstanceType(aws.InstanceType.M3Xlarge)

  case object M32xlarge extends InstanceType(aws.InstanceType.M32xlarge)

  case object M4Large extends InstanceType(aws.InstanceType.M4Large)

  case object M4Xlarge extends InstanceType(aws.InstanceType.M4Xlarge)

  case object M42xlarge extends InstanceType(aws.InstanceType.M42xlarge)

  case object M44xlarge extends InstanceType(aws.InstanceType.M44xlarge)

  case object M410xlarge extends InstanceType(aws.InstanceType.M410xlarge)

  case object M416xlarge extends InstanceType(aws.InstanceType.M416xlarge)

  case object M2Xlarge extends InstanceType(aws.InstanceType.M2Xlarge)

  case object M22xlarge extends InstanceType(aws.InstanceType.M22xlarge)

  case object M24xlarge extends InstanceType(aws.InstanceType.M24xlarge)

  case object Cr18xlarge extends InstanceType(aws.InstanceType.Cr18xlarge)

  case object R3Large extends InstanceType(aws.InstanceType.R3Large)

  case object R3Xlarge extends InstanceType(aws.InstanceType.R3Xlarge)

  case object R32xlarge extends InstanceType(aws.InstanceType.R32xlarge)

  case object R34xlarge extends InstanceType(aws.InstanceType.R34xlarge)

  case object R38xlarge extends InstanceType(aws.InstanceType.R38xlarge)

  case object R4Large extends InstanceType(aws.InstanceType.R4Large)

  case object R4Xlarge extends InstanceType(aws.InstanceType.R4Xlarge)

  case object R42xlarge extends InstanceType(aws.InstanceType.R42xlarge)

  case object R44xlarge extends InstanceType(aws.InstanceType.R44xlarge)

  case object R48xlarge extends InstanceType(aws.InstanceType.R48xlarge)

  case object R416xlarge extends InstanceType(aws.InstanceType.R416xlarge)

  case object R5Large extends InstanceType(aws.InstanceType.R5Large)

  case object R5Xlarge extends InstanceType(aws.InstanceType.R5Xlarge)

  case object R52xlarge extends InstanceType(aws.InstanceType.R52xlarge)

  case object R54xlarge extends InstanceType(aws.InstanceType.R54xlarge)

  case object R58xlarge extends InstanceType(aws.InstanceType.R58xlarge)

  case object R512xlarge extends InstanceType(aws.InstanceType.R512xlarge)

  case object R516xlarge extends InstanceType(aws.InstanceType.R516xlarge)

  case object R524xlarge extends InstanceType(aws.InstanceType.R524xlarge)

  case object R5Metal extends InstanceType(aws.InstanceType.R5Metal)

  case object R5aLarge extends InstanceType(aws.InstanceType.R5aLarge)

  case object R5aXlarge extends InstanceType(aws.InstanceType.R5aXlarge)

  case object R5a2xlarge extends InstanceType(aws.InstanceType.R5a2xlarge)

  case object R5a4xlarge extends InstanceType(aws.InstanceType.R5a4xlarge)

  case object R5a8xlarge extends InstanceType(aws.InstanceType.R5a8xlarge)

  case object R5a12xlarge extends InstanceType(aws.InstanceType.R5a12xlarge)

  case object R5a16xlarge extends InstanceType(aws.InstanceType.R5a16xlarge)

  case object R5a24xlarge extends InstanceType(aws.InstanceType.R5a24xlarge)

  case object R5bLarge extends InstanceType(aws.InstanceType.R5bLarge)

  case object R5bXlarge extends InstanceType(aws.InstanceType.R5bXlarge)

  case object R5b2xlarge extends InstanceType(aws.InstanceType.R5b2xlarge)

  case object R5b4xlarge extends InstanceType(aws.InstanceType.R5b4xlarge)

  case object R5b8xlarge extends InstanceType(aws.InstanceType.R5b8xlarge)

  case object R5b12xlarge extends InstanceType(aws.InstanceType.R5b12xlarge)

  case object R5b16xlarge extends InstanceType(aws.InstanceType.R5b16xlarge)

  case object R5b24xlarge extends InstanceType(aws.InstanceType.R5b24xlarge)

  case object R5bMetal extends InstanceType(aws.InstanceType.R5bMetal)

  case object R5dLarge extends InstanceType(aws.InstanceType.R5dLarge)

  case object R5dXlarge extends InstanceType(aws.InstanceType.R5dXlarge)

  case object R5d2xlarge extends InstanceType(aws.InstanceType.R5d2xlarge)

  case object R5d4xlarge extends InstanceType(aws.InstanceType.R5d4xlarge)

  case object R5d8xlarge extends InstanceType(aws.InstanceType.R5d8xlarge)

  case object R5d12xlarge extends InstanceType(aws.InstanceType.R5d12xlarge)

  case object R5d16xlarge extends InstanceType(aws.InstanceType.R5d16xlarge)

  case object R5d24xlarge extends InstanceType(aws.InstanceType.R5d24xlarge)

  case object R5dMetal extends InstanceType(aws.InstanceType.R5dMetal)

  case object R5adLarge extends InstanceType(aws.InstanceType.R5adLarge)

  case object R5adXlarge extends InstanceType(aws.InstanceType.R5adXlarge)

  case object R5ad2xlarge extends InstanceType(aws.InstanceType.R5ad2xlarge)

  case object R5ad4xlarge extends InstanceType(aws.InstanceType.R5ad4xlarge)

  case object R5ad8xlarge extends InstanceType(aws.InstanceType.R5ad8xlarge)

  case object R5ad12xlarge extends InstanceType(aws.InstanceType.R5ad12xlarge)

  case object R5ad16xlarge extends InstanceType(aws.InstanceType.R5ad16xlarge)

  case object R5ad24xlarge extends InstanceType(aws.InstanceType.R5ad24xlarge)

  case object R6gMetal extends InstanceType(aws.InstanceType.R6gMetal)

  case object R6gMedium extends InstanceType(aws.InstanceType.R6gMedium)

  case object R6gLarge extends InstanceType(aws.InstanceType.R6gLarge)

  case object R6gXlarge extends InstanceType(aws.InstanceType.R6gXlarge)

  case object R6g2xlarge extends InstanceType(aws.InstanceType.R6g2xlarge)

  case object R6g4xlarge extends InstanceType(aws.InstanceType.R6g4xlarge)

  case object R6g8xlarge extends InstanceType(aws.InstanceType.R6g8xlarge)

  case object R6g12xlarge extends InstanceType(aws.InstanceType.R6g12xlarge)

  case object R6g16xlarge extends InstanceType(aws.InstanceType.R6g16xlarge)

  case object R6gdMetal extends InstanceType(aws.InstanceType.R6gdMetal)

  case object R6gdMedium extends InstanceType(aws.InstanceType.R6gdMedium)

  case object R6gdLarge extends InstanceType(aws.InstanceType.R6gdLarge)

  case object R6gdXlarge extends InstanceType(aws.InstanceType.R6gdXlarge)

  case object R6gd2xlarge extends InstanceType(aws.InstanceType.R6gd2xlarge)

  case object R6gd4xlarge extends InstanceType(aws.InstanceType.R6gd4xlarge)

  case object R6gd8xlarge extends InstanceType(aws.InstanceType.R6gd8xlarge)

  case object R6gd12xlarge extends InstanceType(aws.InstanceType.R6gd12xlarge)

  case object R6gd16xlarge extends InstanceType(aws.InstanceType.R6gd16xlarge)

  case object X116xlarge extends InstanceType(aws.InstanceType.X116xlarge)

  case object X132xlarge extends InstanceType(aws.InstanceType.X132xlarge)

  case object X1eXlarge extends InstanceType(aws.InstanceType.X1eXlarge)

  case object X1e2xlarge extends InstanceType(aws.InstanceType.X1e2xlarge)

  case object X1e4xlarge extends InstanceType(aws.InstanceType.X1e4xlarge)

  case object X1e8xlarge extends InstanceType(aws.InstanceType.X1e8xlarge)

  case object X1e16xlarge extends InstanceType(aws.InstanceType.X1e16xlarge)

  case object X1e32xlarge extends InstanceType(aws.InstanceType.X1e32xlarge)

  case object I2Xlarge extends InstanceType(aws.InstanceType.I2Xlarge)

  case object I22xlarge extends InstanceType(aws.InstanceType.I22xlarge)

  case object I24xlarge extends InstanceType(aws.InstanceType.I24xlarge)

  case object I28xlarge extends InstanceType(aws.InstanceType.I28xlarge)

  case object I3Large extends InstanceType(aws.InstanceType.I3Large)

  case object I3Xlarge extends InstanceType(aws.InstanceType.I3Xlarge)

  case object I32xlarge extends InstanceType(aws.InstanceType.I32xlarge)

  case object I34xlarge extends InstanceType(aws.InstanceType.I34xlarge)

  case object I38xlarge extends InstanceType(aws.InstanceType.I38xlarge)

  case object I316xlarge extends InstanceType(aws.InstanceType.I316xlarge)

  case object I3Metal extends InstanceType(aws.InstanceType.I3Metal)

  case object I3enLarge extends InstanceType(aws.InstanceType.I3enLarge)

  case object I3enXlarge extends InstanceType(aws.InstanceType.I3enXlarge)

  case object I3en2xlarge extends InstanceType(aws.InstanceType.I3en2xlarge)

  case object I3en3xlarge extends InstanceType(aws.InstanceType.I3en3xlarge)

  case object I3en6xlarge extends InstanceType(aws.InstanceType.I3en6xlarge)

  case object I3en12xlarge extends InstanceType(aws.InstanceType.I3en12xlarge)

  case object I3en24xlarge extends InstanceType(aws.InstanceType.I3en24xlarge)

  case object I3enMetal extends InstanceType(aws.InstanceType.I3enMetal)

  case object Hi14xlarge extends InstanceType(aws.InstanceType.Hi14xlarge)

  case object Hs18xlarge extends InstanceType(aws.InstanceType.Hs18xlarge)

  case object C1Medium extends InstanceType(aws.InstanceType.C1Medium)

  case object C1Xlarge extends InstanceType(aws.InstanceType.C1Xlarge)

  case object C3Large extends InstanceType(aws.InstanceType.C3Large)

  case object C3Xlarge extends InstanceType(aws.InstanceType.C3Xlarge)

  case object C32xlarge extends InstanceType(aws.InstanceType.C32xlarge)

  case object C34xlarge extends InstanceType(aws.InstanceType.C34xlarge)

  case object C38xlarge extends InstanceType(aws.InstanceType.C38xlarge)

  case object C4Large extends InstanceType(aws.InstanceType.C4Large)

  case object C4Xlarge extends InstanceType(aws.InstanceType.C4Xlarge)

  case object C42xlarge extends InstanceType(aws.InstanceType.C42xlarge)

  case object C44xlarge extends InstanceType(aws.InstanceType.C44xlarge)

  case object C48xlarge extends InstanceType(aws.InstanceType.C48xlarge)

  case object C5Large extends InstanceType(aws.InstanceType.C5Large)

  case object C5Xlarge extends InstanceType(aws.InstanceType.C5Xlarge)

  case object C52xlarge extends InstanceType(aws.InstanceType.C52xlarge)

  case object C54xlarge extends InstanceType(aws.InstanceType.C54xlarge)

  case object C59xlarge extends InstanceType(aws.InstanceType.C59xlarge)

  case object C512xlarge extends InstanceType(aws.InstanceType.C512xlarge)

  case object C518xlarge extends InstanceType(aws.InstanceType.C518xlarge)

  case object C524xlarge extends InstanceType(aws.InstanceType.C524xlarge)

  case object C5Metal extends InstanceType(aws.InstanceType.C5Metal)

  case object C5aLarge extends InstanceType(aws.InstanceType.C5aLarge)

  case object C5aXlarge extends InstanceType(aws.InstanceType.C5aXlarge)

  case object C5a2xlarge extends InstanceType(aws.InstanceType.C5a2xlarge)

  case object C5a4xlarge extends InstanceType(aws.InstanceType.C5a4xlarge)

  case object C5a8xlarge extends InstanceType(aws.InstanceType.C5a8xlarge)

  case object C5a12xlarge extends InstanceType(aws.InstanceType.C5a12xlarge)

  case object C5a16xlarge extends InstanceType(aws.InstanceType.C5a16xlarge)

  case object C5a24xlarge extends InstanceType(aws.InstanceType.C5a24xlarge)

  case object C5adLarge extends InstanceType(aws.InstanceType.C5adLarge)

  case object C5adXlarge extends InstanceType(aws.InstanceType.C5adXlarge)

  case object C5ad2xlarge extends InstanceType(aws.InstanceType.C5ad2xlarge)

  case object C5ad4xlarge extends InstanceType(aws.InstanceType.C5ad4xlarge)

  case object C5ad8xlarge extends InstanceType(aws.InstanceType.C5ad8xlarge)

  case object C5ad12xlarge extends InstanceType(aws.InstanceType.C5ad12xlarge)

  case object C5ad16xlarge extends InstanceType(aws.InstanceType.C5ad16xlarge)

  case object C5ad24xlarge extends InstanceType(aws.InstanceType.C5ad24xlarge)

  case object C5dLarge extends InstanceType(aws.InstanceType.C5dLarge)

  case object C5dXlarge extends InstanceType(aws.InstanceType.C5dXlarge)

  case object C5d2xlarge extends InstanceType(aws.InstanceType.C5d2xlarge)

  case object C5d4xlarge extends InstanceType(aws.InstanceType.C5d4xlarge)

  case object C5d9xlarge extends InstanceType(aws.InstanceType.C5d9xlarge)

  case object C5d12xlarge extends InstanceType(aws.InstanceType.C5d12xlarge)

  case object C5d18xlarge extends InstanceType(aws.InstanceType.C5d18xlarge)

  case object C5d24xlarge extends InstanceType(aws.InstanceType.C5d24xlarge)

  case object C5dMetal extends InstanceType(aws.InstanceType.C5dMetal)

  case object C5nLarge extends InstanceType(aws.InstanceType.C5nLarge)

  case object C5nXlarge extends InstanceType(aws.InstanceType.C5nXlarge)

  case object C5n2xlarge extends InstanceType(aws.InstanceType.C5n2xlarge)

  case object C5n4xlarge extends InstanceType(aws.InstanceType.C5n4xlarge)

  case object C5n9xlarge extends InstanceType(aws.InstanceType.C5n9xlarge)

  case object C5n18xlarge extends InstanceType(aws.InstanceType.C5n18xlarge)

  case object C5nMetal extends InstanceType(aws.InstanceType.C5nMetal)

  case object C6gMetal extends InstanceType(aws.InstanceType.C6gMetal)

  case object C6gMedium extends InstanceType(aws.InstanceType.C6gMedium)

  case object C6gLarge extends InstanceType(aws.InstanceType.C6gLarge)

  case object C6gXlarge extends InstanceType(aws.InstanceType.C6gXlarge)

  case object C6g2xlarge extends InstanceType(aws.InstanceType.C6g2xlarge)

  case object C6g4xlarge extends InstanceType(aws.InstanceType.C6g4xlarge)

  case object C6g8xlarge extends InstanceType(aws.InstanceType.C6g8xlarge)

  case object C6g12xlarge extends InstanceType(aws.InstanceType.C6g12xlarge)

  case object C6g16xlarge extends InstanceType(aws.InstanceType.C6g16xlarge)

  case object C6gdMetal extends InstanceType(aws.InstanceType.C6gdMetal)

  case object C6gdMedium extends InstanceType(aws.InstanceType.C6gdMedium)

  case object C6gdLarge extends InstanceType(aws.InstanceType.C6gdLarge)

  case object C6gdXlarge extends InstanceType(aws.InstanceType.C6gdXlarge)

  case object C6gd2xlarge extends InstanceType(aws.InstanceType.C6gd2xlarge)

  case object C6gd4xlarge extends InstanceType(aws.InstanceType.C6gd4xlarge)

  case object C6gd8xlarge extends InstanceType(aws.InstanceType.C6gd8xlarge)

  case object C6gd12xlarge extends InstanceType(aws.InstanceType.C6gd12xlarge)

  case object C6gd16xlarge extends InstanceType(aws.InstanceType.C6gd16xlarge)

  case object C6gnMedium extends InstanceType(aws.InstanceType.C6gnMedium)

  case object C6gnLarge extends InstanceType(aws.InstanceType.C6gnLarge)

  case object C6gnXlarge extends InstanceType(aws.InstanceType.C6gnXlarge)

  case object C6gn2xlarge extends InstanceType(aws.InstanceType.C6gn2xlarge)

  case object C6gn4xlarge extends InstanceType(aws.InstanceType.C6gn4xlarge)

  case object C6gn8xlarge extends InstanceType(aws.InstanceType.C6gn8xlarge)

  case object C6gn12xlarge extends InstanceType(aws.InstanceType.C6gn12xlarge)

  case object C6gn16xlarge extends InstanceType(aws.InstanceType.C6gn16xlarge)

  case object Cc14xlarge extends InstanceType(aws.InstanceType.Cc14xlarge)

  case object Cc28xlarge extends InstanceType(aws.InstanceType.Cc28xlarge)

  case object G22xlarge extends InstanceType(aws.InstanceType.G22xlarge)

  case object G28xlarge extends InstanceType(aws.InstanceType.G28xlarge)

  case object G34xlarge extends InstanceType(aws.InstanceType.G34xlarge)

  case object G38xlarge extends InstanceType(aws.InstanceType.G38xlarge)

  case object G316xlarge extends InstanceType(aws.InstanceType.G316xlarge)

  case object G3sXlarge extends InstanceType(aws.InstanceType.G3sXlarge)

  case object G4ad4xlarge extends InstanceType(aws.InstanceType.G4ad4xlarge)

  case object G4ad8xlarge extends InstanceType(aws.InstanceType.G4ad8xlarge)

  case object G4ad16xlarge extends InstanceType(aws.InstanceType.G4ad16xlarge)

  case object G4dnXlarge extends InstanceType(aws.InstanceType.G4dnXlarge)

  case object G4dn2xlarge extends InstanceType(aws.InstanceType.G4dn2xlarge)

  case object G4dn4xlarge extends InstanceType(aws.InstanceType.G4dn4xlarge)

  case object G4dn8xlarge extends InstanceType(aws.InstanceType.G4dn8xlarge)

  case object G4dn12xlarge extends InstanceType(aws.InstanceType.G4dn12xlarge)

  case object G4dn16xlarge extends InstanceType(aws.InstanceType.G4dn16xlarge)

  case object G4dnMetal extends InstanceType(aws.InstanceType.G4dnMetal)

  case object Cg14xlarge extends InstanceType(aws.InstanceType.Cg14xlarge)

  case object P2Xlarge extends InstanceType(aws.InstanceType.P2Xlarge)

  case object P28xlarge extends InstanceType(aws.InstanceType.P28xlarge)

  case object P216xlarge extends InstanceType(aws.InstanceType.P216xlarge)

  case object P32xlarge extends InstanceType(aws.InstanceType.P32xlarge)

  case object P38xlarge extends InstanceType(aws.InstanceType.P38xlarge)

  case object P316xlarge extends InstanceType(aws.InstanceType.P316xlarge)

  case object P3dn24xlarge extends InstanceType(aws.InstanceType.P3dn24xlarge)

  case object P4d24xlarge extends InstanceType(aws.InstanceType.P4d24xlarge)

  case object D2Xlarge extends InstanceType(aws.InstanceType.D2Xlarge)

  case object D22xlarge extends InstanceType(aws.InstanceType.D22xlarge)

  case object D24xlarge extends InstanceType(aws.InstanceType.D24xlarge)

  case object D28xlarge extends InstanceType(aws.InstanceType.D28xlarge)

  case object D3Xlarge extends InstanceType(aws.InstanceType.D3Xlarge)

  case object D32xlarge extends InstanceType(aws.InstanceType.D32xlarge)

  case object D34xlarge extends InstanceType(aws.InstanceType.D34xlarge)

  case object D38xlarge extends InstanceType(aws.InstanceType.D38xlarge)

  case object D3enXlarge extends InstanceType(aws.InstanceType.D3enXlarge)

  case object D3en2xlarge extends InstanceType(aws.InstanceType.D3en2xlarge)

  case object D3en4xlarge extends InstanceType(aws.InstanceType.D3en4xlarge)

  case object D3en6xlarge extends InstanceType(aws.InstanceType.D3en6xlarge)

  case object D3en8xlarge extends InstanceType(aws.InstanceType.D3en8xlarge)

  case object D3en12xlarge extends InstanceType(aws.InstanceType.D3en12xlarge)

  case object F12xlarge extends InstanceType(aws.InstanceType.F12xlarge)

  case object F14xlarge extends InstanceType(aws.InstanceType.F14xlarge)

  case object F116xlarge extends InstanceType(aws.InstanceType.F116xlarge)

  case object M5Large extends InstanceType(aws.InstanceType.M5Large)

  case object M5Xlarge extends InstanceType(aws.InstanceType.M5Xlarge)

  case object M52xlarge extends InstanceType(aws.InstanceType.M52xlarge)

  case object M54xlarge extends InstanceType(aws.InstanceType.M54xlarge)

  case object M58xlarge extends InstanceType(aws.InstanceType.M58xlarge)

  case object M512xlarge extends InstanceType(aws.InstanceType.M512xlarge)

  case object M516xlarge extends InstanceType(aws.InstanceType.M516xlarge)

  case object M524xlarge extends InstanceType(aws.InstanceType.M524xlarge)

  case object M5Metal extends InstanceType(aws.InstanceType.M5Metal)

  case object M5aLarge extends InstanceType(aws.InstanceType.M5aLarge)

  case object M5aXlarge extends InstanceType(aws.InstanceType.M5aXlarge)

  case object M5a2xlarge extends InstanceType(aws.InstanceType.M5a2xlarge)

  case object M5a4xlarge extends InstanceType(aws.InstanceType.M5a4xlarge)

  case object M5a8xlarge extends InstanceType(aws.InstanceType.M5a8xlarge)

  case object M5a12xlarge extends InstanceType(aws.InstanceType.M5a12xlarge)

  case object M5a16xlarge extends InstanceType(aws.InstanceType.M5a16xlarge)

  case object M5a24xlarge extends InstanceType(aws.InstanceType.M5a24xlarge)

  case object M5dLarge extends InstanceType(aws.InstanceType.M5dLarge)

  case object M5dXlarge extends InstanceType(aws.InstanceType.M5dXlarge)

  case object M5d2xlarge extends InstanceType(aws.InstanceType.M5d2xlarge)

  case object M5d4xlarge extends InstanceType(aws.InstanceType.M5d4xlarge)

  case object M5d8xlarge extends InstanceType(aws.InstanceType.M5d8xlarge)

  case object M5d12xlarge extends InstanceType(aws.InstanceType.M5d12xlarge)

  case object M5d16xlarge extends InstanceType(aws.InstanceType.M5d16xlarge)

  case object M5d24xlarge extends InstanceType(aws.InstanceType.M5d24xlarge)

  case object M5dMetal extends InstanceType(aws.InstanceType.M5dMetal)

  case object M5adLarge extends InstanceType(aws.InstanceType.M5adLarge)

  case object M5adXlarge extends InstanceType(aws.InstanceType.M5adXlarge)

  case object M5ad2xlarge extends InstanceType(aws.InstanceType.M5ad2xlarge)

  case object M5ad4xlarge extends InstanceType(aws.InstanceType.M5ad4xlarge)

  case object M5ad8xlarge extends InstanceType(aws.InstanceType.M5ad8xlarge)

  case object M5ad12xlarge extends InstanceType(aws.InstanceType.M5ad12xlarge)

  case object M5ad16xlarge extends InstanceType(aws.InstanceType.M5ad16xlarge)

  case object M5ad24xlarge extends InstanceType(aws.InstanceType.M5ad24xlarge)

  case object M5znLarge extends InstanceType(aws.InstanceType.M5znLarge)

  case object M5znXlarge extends InstanceType(aws.InstanceType.M5znXlarge)

  case object M5zn2xlarge extends InstanceType(aws.InstanceType.M5zn2xlarge)

  case object M5zn3xlarge extends InstanceType(aws.InstanceType.M5zn3xlarge)

  case object M5zn6xlarge extends InstanceType(aws.InstanceType.M5zn6xlarge)

  case object M5zn12xlarge extends InstanceType(aws.InstanceType.M5zn12xlarge)

  case object M5znMetal extends InstanceType(aws.InstanceType.M5znMetal)

  case object H12xlarge extends InstanceType(aws.InstanceType.H12xlarge)

  case object H14xlarge extends InstanceType(aws.InstanceType.H14xlarge)

  case object H18xlarge extends InstanceType(aws.InstanceType.H18xlarge)

  case object H116xlarge extends InstanceType(aws.InstanceType.H116xlarge)

  case object Z1dLarge extends InstanceType(aws.InstanceType.Z1dLarge)

  case object Z1dXlarge extends InstanceType(aws.InstanceType.Z1dXlarge)

  case object Z1d2xlarge extends InstanceType(aws.InstanceType.Z1d2xlarge)

  case object Z1d3xlarge extends InstanceType(aws.InstanceType.Z1d3xlarge)

  case object Z1d6xlarge extends InstanceType(aws.InstanceType.Z1d6xlarge)

  case object Z1d12xlarge extends InstanceType(aws.InstanceType.Z1d12xlarge)

  case object Z1dMetal extends InstanceType(aws.InstanceType.Z1dMetal)

  case object U6tb1Metal extends InstanceType(aws.InstanceType.U6tb1Metal)

  case object U9tb1Metal extends InstanceType(aws.InstanceType.U9tb1Metal)

  case object U12tb1Metal extends InstanceType(aws.InstanceType.U12tb1Metal)

  case object U18tb1Metal extends InstanceType(aws.InstanceType.U18tb1Metal)

  case object U24tb1Metal extends InstanceType(aws.InstanceType.U24tb1Metal)

  case object A1Medium extends InstanceType(aws.InstanceType.A1Medium)

  case object A1Large extends InstanceType(aws.InstanceType.A1Large)

  case object A1Xlarge extends InstanceType(aws.InstanceType.A1Xlarge)

  case object A12xlarge extends InstanceType(aws.InstanceType.A12xlarge)

  case object A14xlarge extends InstanceType(aws.InstanceType.A14xlarge)

  case object A1Metal extends InstanceType(aws.InstanceType.A1Metal)

  case object M5dnLarge extends InstanceType(aws.InstanceType.M5dnLarge)

  case object M5dnXlarge extends InstanceType(aws.InstanceType.M5dnXlarge)

  case object M5dn2xlarge extends InstanceType(aws.InstanceType.M5dn2xlarge)

  case object M5dn4xlarge extends InstanceType(aws.InstanceType.M5dn4xlarge)

  case object M5dn8xlarge extends InstanceType(aws.InstanceType.M5dn8xlarge)

  case object M5dn12xlarge extends InstanceType(aws.InstanceType.M5dn12xlarge)

  case object M5dn16xlarge extends InstanceType(aws.InstanceType.M5dn16xlarge)

  case object M5dn24xlarge extends InstanceType(aws.InstanceType.M5dn24xlarge)

  case object M5nLarge extends InstanceType(aws.InstanceType.M5nLarge)

  case object M5nXlarge extends InstanceType(aws.InstanceType.M5nXlarge)

  case object M5n2xlarge extends InstanceType(aws.InstanceType.M5n2xlarge)

  case object M5n4xlarge extends InstanceType(aws.InstanceType.M5n4xlarge)

  case object M5n8xlarge extends InstanceType(aws.InstanceType.M5n8xlarge)

  case object M5n12xlarge extends InstanceType(aws.InstanceType.M5n12xlarge)

  case object M5n16xlarge extends InstanceType(aws.InstanceType.M5n16xlarge)

  case object M5n24xlarge extends InstanceType(aws.InstanceType.M5n24xlarge)

  case object R5dnLarge extends InstanceType(aws.InstanceType.R5dnLarge)

  case object R5dnXlarge extends InstanceType(aws.InstanceType.R5dnXlarge)

  case object R5dn2xlarge extends InstanceType(aws.InstanceType.R5dn2xlarge)

  case object R5dn4xlarge extends InstanceType(aws.InstanceType.R5dn4xlarge)

  case object R5dn8xlarge extends InstanceType(aws.InstanceType.R5dn8xlarge)

  case object R5dn12xlarge extends InstanceType(aws.InstanceType.R5dn12xlarge)

  case object R5dn16xlarge extends InstanceType(aws.InstanceType.R5dn16xlarge)

  case object R5dn24xlarge extends InstanceType(aws.InstanceType.R5dn24xlarge)

  case object R5nLarge extends InstanceType(aws.InstanceType.R5nLarge)

  case object R5nXlarge extends InstanceType(aws.InstanceType.R5nXlarge)

  case object R5n2xlarge extends InstanceType(aws.InstanceType.R5n2xlarge)

  case object R5n4xlarge extends InstanceType(aws.InstanceType.R5n4xlarge)

  case object R5n8xlarge extends InstanceType(aws.InstanceType.R5n8xlarge)

  case object R5n12xlarge extends InstanceType(aws.InstanceType.R5n12xlarge)

  case object R5n16xlarge extends InstanceType(aws.InstanceType.R5n16xlarge)

  case object R5n24xlarge extends InstanceType(aws.InstanceType.R5n24xlarge)

  case object Inf1Xlarge extends InstanceType(aws.InstanceType.Inf1Xlarge)

  case object Inf12xlarge extends InstanceType(aws.InstanceType.Inf12xlarge)

  case object Inf16xlarge extends InstanceType(aws.InstanceType.Inf16xlarge)

  case object Inf124xlarge extends InstanceType(aws.InstanceType.Inf124xlarge)

  case object M6gMetal extends InstanceType(aws.InstanceType.M6gMetal)

  case object M6gMedium extends InstanceType(aws.InstanceType.M6gMedium)

  case object M6gLarge extends InstanceType(aws.InstanceType.M6gLarge)

  case object M6gXlarge extends InstanceType(aws.InstanceType.M6gXlarge)

  case object M6g2xlarge extends InstanceType(aws.InstanceType.M6g2xlarge)

  case object M6g4xlarge extends InstanceType(aws.InstanceType.M6g4xlarge)

  case object M6g8xlarge extends InstanceType(aws.InstanceType.M6g8xlarge)

  case object M6g12xlarge extends InstanceType(aws.InstanceType.M6g12xlarge)

  case object M6g16xlarge extends InstanceType(aws.InstanceType.M6g16xlarge)

  case object M6gdMetal extends InstanceType(aws.InstanceType.M6gdMetal)

  case object M6gdMedium extends InstanceType(aws.InstanceType.M6gdMedium)

  case object M6gdLarge extends InstanceType(aws.InstanceType.M6gdLarge)

  case object M6gdXlarge extends InstanceType(aws.InstanceType.M6gdXlarge)

  case object M6gd2xlarge extends InstanceType(aws.InstanceType.M6gd2xlarge)

  case object M6gd4xlarge extends InstanceType(aws.InstanceType.M6gd4xlarge)

  case object M6gd8xlarge extends InstanceType(aws.InstanceType.M6gd8xlarge)

  case object M6gd12xlarge extends InstanceType(aws.InstanceType.M6gd12xlarge)

  case object M6gd16xlarge extends InstanceType(aws.InstanceType.M6gd16xlarge)

  case object Mac1Metal extends InstanceType(aws.InstanceType.Mac1Metal)

  override def values: Seq[InstanceType] = Seq(
    T1Micro, T2Nano, T2Micro, T2Small, T2Medium, T2Large, T2Xlarge, T22xlarge, T3Nano, T3Micro,
    T3Small, T3Medium, T3Large, T3Xlarge, T32xlarge, T3aNano, T3aMicro, T3aSmall, T3aMedium, T3aLarge,
    T3aXlarge, T3a2xlarge, T4gNano, T4gMicro, T4gSmall, T4gMedium, T4gLarge, T4gXlarge, T4g2xlarge, M1Small,
    M1Medium, M1Large, M1Xlarge, M3Medium, M3Large, M3Xlarge, M32xlarge, M4Large, M4Xlarge, M42xlarge, M44xlarge,
    M410xlarge, M416xlarge, M2Xlarge, M22xlarge, M24xlarge, Cr18xlarge, R3Large, R3Xlarge, R32xlarge,
    R34xlarge, R38xlarge, R4Large, R4Xlarge, R42xlarge, R44xlarge, R48xlarge, R416xlarge,
    R5Large, R5Xlarge, R52xlarge, R54xlarge, R58xlarge, R512xlarge, R516xlarge, R524xlarge, R5Metal, R5aLarge,
    R5aXlarge, R5a2xlarge, R5a4xlarge, R5a8xlarge, R5a12xlarge, R5a16xlarge, R5a24xlarge, R5bLarge, R5bXlarge,
    R5b2xlarge, R5b4xlarge, R5b8xlarge, R5b12xlarge, R5b16xlarge, R5b24xlarge, R5bMetal, R5dLarge, R5dXlarge,
    R5d2xlarge, R5d4xlarge, R5d8xlarge, R5d12xlarge, R5d16xlarge, R5d24xlarge, R5dMetal, R5adLarge, R5adXlarge,
    R5ad2xlarge, R5ad4xlarge, R5ad8xlarge, R5ad12xlarge, R5ad16xlarge, R5ad24xlarge, R6gMetal, R6gMedium,
    R6gLarge, R6gXlarge, R6g2xlarge, R6g4xlarge, R6g8xlarge, R6g12xlarge, R6g16xlarge, R6gdMetal, R6gdMedium,
    R6gdLarge, R6gdXlarge, R6gd2xlarge, R6gd4xlarge, R6gd8xlarge, R6gd12xlarge, R6gd16xlarge, X116xlarge,
    X132xlarge, X1eXlarge, X1e2xlarge, X1e4xlarge, X1e8xlarge, X1e16xlarge, X1e32xlarge, I2Xlarge,
    I22xlarge, I24xlarge, I28xlarge, I3Large, I3Xlarge, I32xlarge, I34xlarge, I38xlarge, I316xlarge,
    I3Metal, I3enLarge, I3enXlarge, I3en2xlarge, I3en3xlarge, I3en6xlarge, I3en12xlarge, I3en24xlarge, I3enMetal,
    Hi14xlarge, Hs18xlarge, C1Medium, C1Xlarge, C3Large, C3Xlarge, C32xlarge, C34xlarge,
    C38xlarge, C4Large, C4Xlarge, C42xlarge, C44xlarge, C48xlarge, C5Large, C5Xlarge, C52xlarge,
    C54xlarge, C59xlarge, C512xlarge, C518xlarge, C524xlarge, C5Metal, C5aLarge, C5aXlarge, C5a2xlarge,
    C5a4xlarge, C5a8xlarge, C5a12xlarge, C5a16xlarge, C5a24xlarge, C5adLarge, C5adXlarge, C5ad2xlarge,
    C5ad4xlarge, C5ad8xlarge, C5ad12xlarge, C5ad16xlarge, C5ad24xlarge,
    C5dLarge, C5dXlarge, C5d2xlarge, C5d4xlarge, C5d9xlarge, C5d12xlarge, C5d18xlarge, C5d24xlarge,
    C5dMetal, C5nLarge, C5nXlarge, C5n2xlarge, C5n4xlarge, C5n9xlarge, C5n18xlarge, C5nMetal, C6gMetal, C6gMedium,
    C6gLarge, C6gXlarge, C6g2xlarge, C6g4xlarge, C6g8xlarge, C6g12xlarge, C6g16xlarge, C6gdMetal, C6gdMedium,
    C6gdLarge, C6gdXlarge, C6gd2xlarge, C6gd4xlarge, C6gd8xlarge, C6gd12xlarge, C6gd16xlarge, C6gnMedium, C6gnLarge,
    C6gnXlarge, C6gn2xlarge, C6gn4xlarge, C6gn8xlarge, C6gn12xlarge, C6gn16xlarge, Cc14xlarge, Cc28xlarge, G22xlarge,
    G28xlarge, G34xlarge, G38xlarge, G316xlarge, G3sXlarge, G4ad4xlarge, G4ad8xlarge, G4ad16xlarge, G4dnXlarge,
    G4dn2xlarge, G4dn4xlarge, G4dn8xlarge, G4dn12xlarge, G4dn16xlarge, G4dnMetal, Cg14xlarge, P2Xlarge,
    P28xlarge, P216xlarge, P32xlarge, P38xlarge, P316xlarge, P3dn24xlarge, P4d24xlarge, D2Xlarge, D22xlarge,
    D24xlarge, D28xlarge, F12xlarge, F14xlarge, D3Xlarge, D32xlarge, D34xlarge, D38xlarge, D3enXlarge, D3en2xlarge,
    D3en4xlarge, D3en6xlarge, D3en8xlarge, D3en12xlarge, F116xlarge, M5Large, M5Xlarge, M52xlarge, M54xlarge,
    M58xlarge, M512xlarge, M516xlarge, M524xlarge, M5Metal, M5aLarge, M5aXlarge, M5a2xlarge, M5a4xlarge,
    M5a8xlarge, M5a12xlarge, M5a16xlarge, M5a24xlarge, M5dLarge, M5dXlarge, M5d2xlarge,
    M5d4xlarge, M5d8xlarge, M5d12xlarge, M5d16xlarge, M5d24xlarge, M5dMetal, M5adLarge, M5adXlarge, M5ad2xlarge,
    M5ad4xlarge, M5ad8xlarge, M5ad12xlarge, M5ad16xlarge, M5ad24xlarge, M5znLarge, M5znXlarge, M5zn2xlarge,
    M5zn3xlarge, M5zn6xlarge, M5zn12xlarge, M5znMetal, H12xlarge, H14xlarge, H18xlarge, H116xlarge,
    Z1dLarge, Z1dXlarge, Z1d2xlarge, Z1d3xlarge, Z1d6xlarge, Z1d12xlarge, Z1dMetal, U6tb1Metal, U9tb1Metal,
    U12tb1Metal, U18tb1Metal, U24tb1Metal, A1Medium, A1Large, A1Xlarge, A12xlarge, A14xlarge, A1Metal, M5dnLarge,
    M5dnXlarge, M5dn2xlarge, M5dn4xlarge, M5dn8xlarge, M5dn12xlarge, M5dn16xlarge, M5dn24xlarge, M5nLarge, M5nXlarge,
    M5n2xlarge, M5n4xlarge, M5n8xlarge, M5n12xlarge, M5n16xlarge, M5n24xlarge, R5dnLarge, R5dnXlarge, R5dn2xlarge,
    R5dn4xlarge, R5dn8xlarge, R5dn12xlarge, R5dn16xlarge, R5dn24xlarge, R5nLarge, R5nXlarge, R5n2xlarge, R5n4xlarge,
    R5n8xlarge, R5n12xlarge, R5n16xlarge, R5n24xlarge, Inf1Xlarge, Inf12xlarge, Inf16xlarge, Inf124xlarge, M6gMetal,
    M6gMedium, M6gLarge, M6gXlarge, M6g2xlarge, M6g4xlarge, M6g8xlarge, M6g12xlarge, M6g16xlarge, M6gdMetal,
    M6gdMedium, M6gdLarge, M6gdXlarge, M6gd2xlarge, M6gd4xlarge, M6gd8xlarge, M6gd12xlarge, M6gd16xlarge, Mac1Metal)
}
