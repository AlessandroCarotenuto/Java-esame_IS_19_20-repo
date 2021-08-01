/**
 * 
 */
/**
 * @author carot
 *
 */
module module_info.java {
	exports database;
	exports test;
	exports control;
	exports main;
	exports entity;

	requires java.sql;
	requires junit;
}