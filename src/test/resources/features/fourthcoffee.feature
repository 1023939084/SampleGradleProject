Feature: 
	como cliente quiero poder comprar en fourth coffee

	Scenario: ingresar a fourth coffee
		Given ingreso a fourth coffee
		Then debo ver titulo del home "Fourth Coffee"
		
	Scenario: lista de productos
		Given ingreso a fourth coffee
		Then debo ver 6 productos
		
#	Scenario: elegir producto Bread
#		Given ingreso a fourth coffee
#		When doy clic en el boton Order Now del producto Bread
#		Then debo ir a la pagina Place Your Order: Bread
		
#	Scenario: comprar producto Bread
#		Given que estoy en la pagina Place Your Order: Bread
#		When ingreso el correo "test@test.com"
#		And ingreso la direccion "Cl. 11 #3142, Bogotá"
#		And ingreso cantidad "3"
#		And presiono el boton Place Order
#		Then debo ver Order Confirmation
		
#	Scenario: volver a comprar
#		Given estoy en la pagina Order Confirmation
#		When presiono el boton Continue Shopping
#		Then debo ver titulo del home "Foutrh Coffee"
		
#	Scenario: ir al home
#		Given estoy en la pagina placeorder
#		When doy clic en el botron Home
#		Then debo ver titulo del home "Foutrh Coffee"
