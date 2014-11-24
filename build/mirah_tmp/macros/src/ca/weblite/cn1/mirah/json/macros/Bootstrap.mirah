package ca.weblite.cn1.mirah.json.macros
/**
 *
 * @author shannah
 */
class Bootstrap 
  macro def self.loadExtensions
    @mirah.type_system.extendClass("java.lang.Object", ObjectExtensions.class)
    nil
  end
end

