USE [myDB]
GO

/****** Object:  Table [dbo].[ORDER]    Script Date: 08/22/2014 18:56:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[ORDER](
	[MessageId] [varchar](50) NULL,
	[OrderNumber] [varchar](50) NULL,
	[AccountName] [varchar](50) NULL,
	[ContactNumber] [varchar](50) NULL,
	[OrderAmount] [varchar](50) NULL,
	[OrderStatus] [varchar](50) NULL,
	[BillingAddress] [varchar](50) NULL,
	[ShippingAddress] [varchar](50) NULL,
	[CreatdBy] [varchar](50) NULL,
	[ActivatedBy] [varchar](50) NULL,
	[TS] [varchar](50) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


