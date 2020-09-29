DROP TABLE tb_velocity_template;
CREATE TABLE tb_velocity_template (
id_template varchar (40) NOT NULL ,
template_definition LONGTEXT NOT NULL ,
template_timestamp datetime
); 

select * from tb_velocity_template;
delete from tb_velocity_template;

INSERT INTO  tb_velocity_template (id_template, template_definition) VALUES('exemple', '## This is an example velocity template
#set ($companyName = "Gildas Compagny")
#set( $this = "Velocity")
$this is great!
#foreach( $name in $list ) 
	$name is great! 
#end
#parse("exemple2")
$this is great!
#set( $condition = true)');

INSERT INTO  tb_velocity_template (id_template, template_definition) VALUES('exemple2', 'Gildas test : $companyName');	