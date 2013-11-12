<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet href="XML2FLATII.xsl" type="text/xsl"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
   <xsl:output method="text" indent="no" encoding="UTF-8" />
   <xsl:template match="/">
  
   <xsl:for-each select="//shippingOrder/order/orderItemList/org.ordermgmt.OrderItem"><xsl:value-of select="merchantSKU"/><xsl:text>		</xsl:text><xsl:value-of select="quantity"/><xsl:text>&#10;</xsl:text></xsl:for-each>
   
   </xsl:template>
</xsl:stylesheet>