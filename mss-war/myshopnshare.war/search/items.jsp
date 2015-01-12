<div align="center">
<form method="POST"
 action="{concat('http://www.amazon.com/o/dt/assoc/handle-buy-box=',ProductInfo/DetailsAsin)}">
<table border="1" cellpadding="3" cellspacing="3">
<tr>
<td>First Item Image File</td>
<td>First Item yadda, pricing, availability</td>
<td><input type="checkbox" name="{concat('asin.', $Asin)}" value="1"/>Add to my Amazon Cart</td>
</tr>
<tr>
<td>Second Item Image File</td>
<td>Second Item yadda, pricing, availability</td>
<td><input type="checkbox" name="{concat('asin.', $Asin)}" value="1"/>Add to my Amazon Cart</td>
</tr>
</table>


<input type="hidden" name="tag_value" value="YOUR-AFFILIATE-ID"/>
<input type="hidden" name="tag-value" value="YOUR-AFFILIATE-ID"/>
<input type="hidden" name="dev-tag-value" value="YOUR-DEVELOPER-ID"/>
<input type="image"  value="Add this to your Amazon Shopping Cart" name="submit.add-to-cart"
src="http://www.your-site.com/image-files/buy-from-amazon.gif" align="center"
alt="Buy from Amazon" border="0" width="170" height="20"/>
you can always edit your Amazon Shopping Cart at a later date - remove, add, save items...
</form>
</div>
